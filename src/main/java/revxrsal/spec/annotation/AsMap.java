/*
 * This file is part of Cream, licensed under the MIT License.
 *
 *  Copyright (c) Revxrsal <reflxction.github@gmail.com>
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */
package revxrsal.spec.annotation;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Returns the {@link java.util.Map} representation of a given
 * {@link ConfigSpec} interface.
 * <p>
 * Example:
 * <pre>{@code @ConfigSpec
 * public interface GameSettings {
 *
 *     @Comment("The game cooldown")
 *     default int cooldown() {
 *         return 20;
 *     }
 *
 *     @Comment("The cooldown message")
 *     default String countdownMessage() {
 *         return "Game starts in %countdown%s";
 *     }
 *
 *     @AsMap(AsMap.Behavior.CLONE)
 *     Map<String, Object> asMap();
 *
 * }}</pre>
 */
@HandledByProxy
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AsMap {

    /**
     * Decides what to do with the internal map
     *
     * @return The behavior of this {@link AsMap} method
     */
    @NotNull Behavior value() default Behavior.IMMUTABLE_VIEW;

    enum Behavior {

        /**
         * Creates a (shallow) copy of the underlying map
         */
        CLONE,

        /**
         * Creates an immutable view of the underlying map. This is the default
         * behavior
         */
        IMMUTABLE_VIEW,

        /**
         * Returns the underlying map as-is. Modifying this map will modify
         * the actual config spec.
         */
        UNDERLYING_MAP
    }
}
