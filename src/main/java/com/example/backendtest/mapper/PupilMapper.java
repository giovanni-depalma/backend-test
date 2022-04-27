package com.example.backendtest.mapper;

import com.example.backendtest.core.domain.Education;
import com.example.backendtest.core.domain.Pupil;
import com.example.backendtest.presenter.api.EducationItemResource;
import com.example.backendtest.presenter.api.PupilRequestResource;
import com.example.backendtest.presenter.api.PupilResource;
import org.mapstruct.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface PupilMapper {

    Pupil toDomain(PupilResource item);

    Pupil toDomain(PupilRequestResource item);

    PupilResource toResource(Pupil item);

    default Map<String, EducationItemResource> map(List<Education> list){
        return list.stream().reduce(new HashMap<>(), (acc, item)->{
            acc.put(item.level(), new EducationItemResource(item.university(), item.year()));
            return acc;
        }, (acc1, acc2)->{
            var map = new HashMap<String, EducationItemResource>();
            map.putAll(acc1);
            map.putAll(acc2);
            return map;
        });
    }

    default List<Education> map(Map<String, EducationItemResource> map){
        return map.entrySet().stream().map(e -> {
            EducationItemResource item = e.getValue();
            return new Education(e.getKey(),item.university(), item.year());
        }).toList();
    }
}
