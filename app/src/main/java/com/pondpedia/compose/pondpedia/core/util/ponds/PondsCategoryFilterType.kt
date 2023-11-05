package com.pondpedia.compose.pondpedia.core.util.ponds

enum class PondsCategoryFilterType {
    /**
     * Unfiltered by categorization status
     */
    ALL,

    /**
     * Filtered by categorization status -> Uncategorized Ponds
     */
    NON_CATEGORIZED,

    /**
     * Filtered by categorization status -> Categorized Ponds
     */
    CATEGORIZED,
}