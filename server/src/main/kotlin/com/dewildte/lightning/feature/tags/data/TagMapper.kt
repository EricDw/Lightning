package com.dewildte.lightning.feature.tags.data

import com.dewildte.lightning.feature.tags.model.Tag
import com.dewildte.lightning.feature.tags.model.TagId
import com.dewildte.lightning.feature.tags.model.TagLabel
import kotlin.uuid.Uuid

class TagMapper {
    fun mapTagToTagDto(tag: Tag): TagDTO {
        return with(tag) {
            TagDTO(
                id = TagIdDTO(value = id.value.toString()),
                label = TagLabelDTO(value = label.value)
            )
        }
    }

    fun mapTagDtoToTag(dto: TagDTO): Tag {
        return with(dto) {
            Tag(
                id = TagId(value = Uuid.parse(dto.id.value)),
                label = TagLabel(value = dto.label.value)
            )
        }
    }
}