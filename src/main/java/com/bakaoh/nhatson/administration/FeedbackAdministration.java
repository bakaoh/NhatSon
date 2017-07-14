package com.bakaoh.nhatson.administration;

import com.bakaoh.nhatson.domain.Feedback;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.PersistentFieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;

public class FeedbackAdministration extends AdministrationConfiguration<Feedback> {

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder.nameField("email").singularName("Phản hồi").pluralName("Phản hồi").build();
    }

    @Override
    public FieldSetConfigurationUnit listView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("email").caption("E-mail")
                .field("subject").caption("Tiêu đề")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit quickView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("name").caption("Người gửi")
                .field("email").caption("E-mail")
                .field("phone").caption("Điện thoại")
                .field("subject").caption("Tiêu đề")
                .field("message").caption("Nội dung")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit showView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("name").caption("Người gửi")
                .field("email").caption("E-mail")
                .field("phone").caption("Điện thoại")
                .field("subject").caption("Tiêu đề")
                .field("message").caption("Nội dung")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit formView(PersistentFieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("name").caption("Người gửi")
                .field("email").caption("E-mail")
                .field("phone").caption("Điện thoại")
                .field("subject").caption("Tiêu đề")
                .field("message").caption("Nội dung")
                .build();
    }
}