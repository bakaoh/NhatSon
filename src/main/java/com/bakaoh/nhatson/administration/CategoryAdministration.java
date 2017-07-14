package com.bakaoh.nhatson.administration;

import com.bakaoh.nhatson.domain.Category;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.PersistentFieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;

import static org.lightadmin.api.config.utils.Editors.wysiwyg;

public class CategoryAdministration extends AdministrationConfiguration<Category> {

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder.nameField("name").singularName("Thể loại").pluralName("Thể loại").build();
    }

    @Override
    public FieldSetConfigurationUnit listView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("name").caption("Tên thể loại")
                .field("newest").caption("Tự động hiện bài mới nhất")
                .field("home").caption("Lên trang chủ")
                .field("parent").caption("Thể loại cha")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit quickView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("name").caption("Tên thể loại")
                .field("newest").caption("Tự động hiện bài mới nhất")
                .field("home").caption("Lên trang chủ")
                .field("parent").caption("Thể loại cha")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit showView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("name").caption("Tên thể loại")
                .field("newest").caption("Tự động hiện bài mới nhất")
                .field("home").caption("Lên trang chủ")
                .field("parent").caption("Thể loại cha")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit formView(PersistentFieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("name").caption("Tên thể loại")
                .field("newest").caption("Tự động hiện bài mới nhất")
                .field("home").caption("Lên trang chủ")
                .field("parent").caption("Thể loại cha")
                .build();
    }
}