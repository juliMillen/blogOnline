package com.jm.blogOnline.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username","message","jwt","status"})
public record AuthResponseDTO(String username, String password, String jwt, boolean status) {
}
