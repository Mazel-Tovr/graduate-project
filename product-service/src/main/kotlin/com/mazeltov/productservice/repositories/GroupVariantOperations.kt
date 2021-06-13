package com.mazeltov.productservice.repositories


import com.mazeltov.productservice.models.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*

/**
 * Интерфейс для взаимодейсятвия с группой вариантов в базе данных
 */
@Repository
interface GroupVariantOperations : JpaRepository<GroupVariant,Long> {
}
