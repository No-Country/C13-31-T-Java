package com.c1331tjava.ServiceApp.config;

import java.util.HashMap;
import java.util.Map;

/**
 * A utility class for managing pagination configuration.
 */
public class PaginationConfig {

    private int page;
    private int limit;
    private int totalItems;
    private int startIndex;
    private int endIndex;

    /**
     * Constructs a PaginationConfig object with the specified parameters.
     *
     * @param page       The current page number.
     * @param limit      The maximum number of items per page.
     * @param totalItems The total number of items available.
     */
    public PaginationConfig(int page, int limit, int totalItems) {
        this.page = page;
        this.limit = limit;
        this.totalItems = totalItems;
        calculateIndices();
    }

    /**
     * Calculates the start and end indices for the current page based on the limit and total items.
     */
    private void calculateIndices() {
        startIndex = (page - 1) * limit;
        endIndex = Math.min(startIndex + limit, totalItems);
    }

    /**
     * Gets the current page number.
     *
     * @return The current page number.
     */
    public int getPage() {
        return page;
    }

    /**
     * Gets the maximum number of items per page.
     *
     * @return The maximum number of items per page.
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Gets the total number of items available.
     *
     * @return The total number of items available.
     */
    public int getTotalItems() {
        return totalItems;
    }

    /**
     * Gets the start index of the items for the current page.
     *
     * @return The start index of the items for the current page.
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * Gets the end index of the items for the current page.
     *
     * @return The end index of the items for the current page.
     */
    public int getEndIndex() {
        return endIndex;
    }

    /**
     * Generates a string representation of the pagination information.
     *
     * @return A string representation of the pagination information.
     */
    @Override
    public String toString() {
        Map<String, Object> infoMap = new HashMap<>();
        infoMap.put("total_items", totalItems);
        infoMap.put("page", page);
        infoMap.put("items_per_page", limit);
        return infoMap.toString();
    }
}

