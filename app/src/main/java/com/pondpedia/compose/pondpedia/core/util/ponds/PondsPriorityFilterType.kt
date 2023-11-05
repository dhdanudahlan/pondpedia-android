package com.pondpedia.compose.pondpedia.core.util.ponds

enum class PondsPriorityFilterType {
    /**
     * Unfiltered by priority status
     */
    ALL,

    /**
     * Filtered by priority status -> Non Prioritized Ponds
     */
    NON_PRIORITIZED,

    /**
     * Filtered by priority status -> Prioritized Ponds
     */
    PRIORITIZED,
}