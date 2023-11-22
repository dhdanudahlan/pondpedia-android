package com.pondpedia.android.pondpedia.core.util.ponds

import androidx.sqlite.db.SimpleSQLiteQuery

object PondsFilterUtils {
    fun getFilteredQuery(
        categoryFilterType: PondsCategoryFilterType = PondsCategoryFilterType.ALL,
        priorityFilterType: PondsPriorityFilterType = PondsPriorityFilterType.ALL,
        harvestFilterType: PondsHarvestFilterType = PondsHarvestFilterType.ALL,
        category: String = "",
    ): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder()
            .append("SELECT * FROM pond_logs WHERE pond_id IN (SELECT pond_id FROM pond_category WHERE")

        when(priorityFilterType) {
            PondsPriorityFilterType.ALL -> {
                simpleQuery.append(" prioritized IS NOT NULL")
            }
            PondsPriorityFilterType.NON_PRIORITIZED -> {
                simpleQuery.append(" prioritized = false")
            }
            PondsPriorityFilterType.PRIORITIZED -> {
                simpleQuery.append(" prioritized = true")
            }
        }
        when(categoryFilterType) {
            PondsCategoryFilterType.ALL -> Unit
            PondsCategoryFilterType.NON_CATEGORIZED -> {
                simpleQuery.append(" AND categorized = false")
            }
            PondsCategoryFilterType.CATEGORIZED -> {
                simpleQuery.append(" AND categorized = true")
            }
        }
        when(harvestFilterType) {
            PondsHarvestFilterType.ALL -> Unit
            PondsHarvestFilterType.NON_HARVESTED -> {
                simpleQuery.append(" AND harvested = false")
            }
            PondsHarvestFilterType.HARVESTED -> {
                simpleQuery.append(" AND harvested = true")
            }
        }

        simpleQuery.append(")")

        simpleQuery.append(" AND log_id IN (SELECT MAX(log_id) FROM pond_logs GROUP BY pond_id)")
        return SimpleSQLiteQuery(simpleQuery.toString())

//        SELECT * FROM pond_logs WHERE pond_id IN (SELECT pond_id FROM pond_category WHERE prioritized IS NOT NULL AND :category IN category_name) AND log_id IN (SELECT MAX(log_id) FROM pond_logs GROUP BY pond_id)
    }
}