package com.bakaoh.nhatson.administration;

import com.bakaoh.nhatson.domain.Article;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.PersistentFieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;

import static org.lightadmin.api.config.utils.Editors.wysiwyg;

public class ArticleAdministration extends AdministrationConfiguration<Article> {

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder.nameField("title").singularName("Trang tin").pluralName("Trang tin").build();
    }

    @Override
    public FieldSetConfigurationUnit listView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("creationDate").caption("Ngày tạo")
                .field("title").caption("Tiêu đề")
                .field("image").caption("Hình ảnh")
                .field("category").caption("Thể loại")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit quickView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("creationDate").caption("Ngày tạo")
                .field("title").caption("Tiêu đề")
                .field("image").caption("Hình ảnh")
                .field("category").caption("Thể loại")
                .field("feature").caption("Dự án nổi bật")
                .field("detail").caption("Nội dung")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit showView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("creationDate").caption("Ngày tạo")
                .field("title").caption("Tiêu đề")
                .field("image").caption("Hình ảnh")
                .field("category").caption("Thể loại")
                .field("feature").caption("Dự án nổi bật")
                .field("detail").caption("Nội dung")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit formView(PersistentFieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("creationDate").caption("Ngày tạo")
                .field("title").caption("Tiêu đề")
                .field("image").caption("Hình ảnh")
                .field("category").caption("Thể loại")
                .field("feature").caption("Dự án nổi bật")
                .field("detail").caption("Nội dung").editor(wysiwyg())
                .build();
    }
}