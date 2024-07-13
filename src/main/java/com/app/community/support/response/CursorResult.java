package com.app.community.support.response;

import java.util.List;

public record CursorResult<T>(
        Integer totalElements,
        Long nextCursor,
        Boolean isLast,
        List<T> content
) {
    public static <T> CursorResult<T> of(List<T> items, int limit, java.util.function.Function<T, Long> cursorExtractor) {
        boolean isLast = items.size() <= limit;
        Long nextCursor = null;
        if (!isLast) {
            T lastItem = items.remove(items.size() - 1);
            nextCursor = cursorExtractor.apply(lastItem);
        }
        return new CursorResult<>(items.size(), nextCursor, isLast, items);
    }
}
