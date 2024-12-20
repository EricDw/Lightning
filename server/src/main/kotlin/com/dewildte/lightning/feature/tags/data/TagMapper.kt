package com.dewildte.lightning.feature.tags.data

import com.dewildte.lightning.dtos.tags.data.TagDTO
import com.dewildte.lightning.dtos.tags.data.TagIdDTO
import com.dewildte.lightning.dtos.tags.data.TagLabelDTO
import com.dewildte.lightning.models.tags.Tag
import com.dewildte.lightning.models.tags.TagId
import com.dewildte.lightning.models.tags.TagLabel
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