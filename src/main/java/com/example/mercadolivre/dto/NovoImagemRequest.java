package com.example.mercadolivre.dto;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class NovoImagemRequest {
	
	@Size(min = 1) @NotNull
	private List<MultipartFile> imagens = new ArrayList<>();

	public NovoImagemRequest(@Size(min = 1) List<MultipartFile> imagens) {
		super();
		this.imagens = imagens;
	}

	public List<MultipartFile> getImagens() {
		// TODO Auto-generated method stub
		return imagens;
	}

	
}
