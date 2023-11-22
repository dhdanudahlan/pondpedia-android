package com.pondpedia.android.pondpedia.core.util.ponds

enum class PondsHarvestFilterType {
    /**
     * Unfiltered by priority status
     */
    ALL,

    /**
     * Filtered by priority status -> Non Prioritized Ponds
     */
    NON_HARVESTED,

    /**
     * Filtered by priority status -> Prioritized Ponds
     */
    HARVESTED,
}