package com.example.mercadolivre.utils;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploaderFake {

	public Set<String> envia(List<MultipartFile> imagens) {
		// TODO Auto-generated method stub
		return imagens.stream().map(imagem -> "http://bucket.io/"+imagem.getOriginalFilename()+"-"+UUID.randomUUID().toString()).collect(Collectors.toSet());
	}

}
