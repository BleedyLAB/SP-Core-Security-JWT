package ru.studentsplatform.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Note {
	private Long id;
	private String textNote;
}
