package com.projetSpringBoot_Ng.projetSg_Ng.email;

import lombok.Getter;

@Getter
public enum EmailTemplateName {

    ACTIVATE_ACCOUNT("activate_account");


    private final String name;
    EmailTemplateName(String name) {
        this.name = name;
    }
}