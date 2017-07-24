package com.bakaoh.nhatson.administration;

import com.bakaoh.nhatson.domain.Photo;
import com.bakaoh.nhatson.util.Utils;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.PersistentFieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.utils.EntityNameExtractor;
import org.lightadmin.api.config.utils.FieldValueRenderer;

public class PhotoAdministration extends AdministrationConfiguration<Photo> {

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder.singularName("Hình ảnh").pluralName("Hình ảnh").nameExtractor(extractor()).build();
    }

    @Override
    public FieldSetConfigurationUnit listView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("image").caption("Hình ảnh")
                .renderable(fieldUrl()).caption("Đường dẫn")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit quickView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("image").caption("Hình ảnh")
                .renderable(fieldUrl()).caption("Đường dẫn")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit showView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("image").caption("Hình ảnh")
                .renderable(fieldUrl()).caption("Đường dẫn")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit formView(PersistentFieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("image").caption("Hình ảnh")
                .build();
    }

    private EntityNameExtractor<Photo> extractor() {
        return new EntityNameExtractor<Photo>() {
            @Override
            public String apply(Photo input) {
                return Utils.getImageUrl(input.getImage());
            }
        };
    }

    private FieldValueRenderer<Photo> fieldUrl() {
        return new FieldValueRenderer<Photo>() {
            @Override
            public String apply(Photo input) {
                return Utils.getImageUrl(input.getImage());
            }
        };
    }
}