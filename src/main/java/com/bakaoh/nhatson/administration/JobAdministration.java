package com.bakaoh.nhatson.administration;

import com.bakaoh.nhatson.domain.Job;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.PersistentFieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;

import static org.lightadmin.api.config.utils.Editors.wysiwyg;

public class JobAdministration extends AdministrationConfiguration<Job> {

    @Override
    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder.pluralName("Tuyển dụng").singularName("Tuyển dụng").nameField("title").build();
    }

    @Override
    public FieldSetConfigurationUnit listView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("postDate").caption("Ngày đăng")
                .field("deadline").caption("Hạn nộp")
                .field("title").caption("Vị trí")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit quickView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("postDate").caption("Ngày đăng")
                .field("deadline").caption("Hạn nộp")
                .field("title").caption("Vị trí")
                .field("experience").caption("Kinh nghiệm")
                .field("diploma").caption("Bằng cấp")
                .field("type").caption("Cấp bậc")
                .field("sex").caption("Giới tính")
                .field("num").caption("Số lượng")
                .field("area").caption("Nơi làm việc")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit showView(FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("postDate").caption("Ngày đăng")
                .field("deadline").caption("Hạn nộp")
                .field("title").caption("Vị trí")
                .field("experience").caption("Kinh nghiệm")
                .field("diploma").caption("Bằng cấp")
                .field("type").caption("Cấp bậc")
                .field("sex").caption("Giới tính")
                .field("num").caption("Số lượng")
                .field("area").caption("Nơi làm việc")
                .field("description").caption("Mô tả công việc")
                .field("requirement").caption("Yêu cầu")
                .field("skill").caption("Kỹ năng")
                .field("benefit").caption("Phúc lợi")
                .build();
    }

    @Override
    public FieldSetConfigurationUnit formView(PersistentFieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("postDate").caption("Ngày đăng")
                .field("deadline").caption("Hạn nộp")
                .field("title").caption("Vị trí")
                .field("experience").caption("Kinh nghiệm")
                .field("diploma").caption("Bằng cấp")
                .field("type").caption("Cấp bậc")
                .field("sex").caption("Giới tính")
                .field("num").caption("Số lượng")
                .field("area").caption("Nơi làm việc")
                .field("description").caption("Mô tả công việc").editor(wysiwyg())
                .field("requirement").caption("Yêu cầu").editor(wysiwyg())
                .field("skill").caption("Kỹ năng").editor(wysiwyg())
                .field("benefit").caption("Phúc lợi").editor(wysiwyg())
                .build();
    }
}