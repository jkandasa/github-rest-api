package org.jkandasa.restclient.github.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.ToString;
import lombok.Data;
import lombok.Builder;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Plan {
    private String name;
    private Long space;

    @JsonProperty("private_repos")
    private Long privateRepos;

    private Long collaborators;
}
