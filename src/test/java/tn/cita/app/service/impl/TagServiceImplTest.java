package tn.cita.app.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import tn.cita.app.constant.AppConstant;
import tn.cita.app.domain.entity.Tag;
import tn.cita.app.dto.TagDto;
import tn.cita.app.exception.wrapper.TagNotFoundException;
import tn.cita.app.repository.TagRepository;
import tn.cita.app.service.TagService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TagServiceImplTest {
	
	@Autowired
	private TagService tagService;
	
	@MockBean
	private TagRepository tagRepository;
	
	@Test
	void givenValidPageOffset_whenFindAll_thenAllTagsBasedOnPageOffsetShouldBeReturned() {
		final var pageOffset = 1;
		final var mockedReturnedList = List.of(
				Tag.builder()
				.id(null)
				.name("barber")
				.build(),
				Tag.builder()
				.id(null)
				.name("manicure")
				.build()
		);
		final var expectedList = List.of(
				TagDto.builder()
				.id(null)
				.name("barber")
				.build(),
				TagDto.builder()
				.id(null)
				.name("manicure")
				.build()
		);
		
		when(this.tagRepository.findAll(PageRequest.of(pageOffset - 1, AppConstant.PAGE_SIZE)))
				.thenReturn(new PageImpl<>(mockedReturnedList));
		
		final var list = this.tagService.findAll(pageOffset);
		assertThat(list)
				.isNotNull()
				.isNotEmpty()
				.hasSameSizeAs(expectedList)
				.allSatisfy(t -> 
					assertThat(t)
							.isNotNull()
							.isInstanceOf(TagDto.class));
		
	}
	
	@Test
	void givenValidId_whenFindById_thenTagDtoShouldBeFound() {
		
		final int id = 1;
		final var tag = Tag.builder()
				.id(id)
				.name("barber")
				.build();
		
		final var expectedTagDto = TagDto.builder()
				.id(id)
				.name("barber")
				.build();
		
		when(this.tagRepository.findById(id))
				.thenReturn(Optional.ofNullable(tag));
		
		final var tagDto = this.tagService.findById(id);
		assertThat(tagDto)
				.isNotNull()
				.isInstanceOf(TagDto.class);
		assertThat(tagDto.getName())
				.isNotBlank()
				.isEqualTo(expectedTagDto.getName());
	}
	
	@Test
	void givenInvalidId_whenFindById_thenTagNotFoundExceptionShouldBeThrown() {
		final int id = 0;
		assertThatExceptionOfType(TagNotFoundException.class)
				.isThrownBy(() -> this.tagService.findById(id))
				.withMessageStartingWith("Tag ")
				.withMessageEndingWith(" not found")
				.withMessage(String.format("Tag with id: %d not found", id));
		
	}
	
	
	
}











