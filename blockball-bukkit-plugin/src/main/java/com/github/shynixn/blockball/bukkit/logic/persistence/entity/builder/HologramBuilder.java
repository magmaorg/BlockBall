package com.github.shynixn.blockball.bukkit.logic.persistence.entity.builder;

import com.github.shynixn.blockball.api.persistence.entity.HologramMeta;
import com.github.shynixn.blockball.bukkit.logic.persistence.entity.PersistenceObject;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.*;

/**
 * Created by Shynixn 2017.
 * <p>
 * Version 1.1
 * <p>
 * MIT License
 * <p>
 * Copyright (c) 2017 by Shynixn
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class HologramBuilder extends PersistenceObject<HologramMeta> implements HologramMeta{
    private List<String> lines = new ArrayList<>();
    private Location location;

    /**
     * Initialize.
     */
    public HologramBuilder() {
    }

    /**
     * Initializes a new hologram builder from the given data.
     *
     * @param data data
     */
    public HologramBuilder(Map<String, Object> data) {
        this.location = Location.deserialize((Map<String, Object>) data.get("location"));
        this.lines = new ArrayList<>((Collection<String>) data.get("lines"));
    }

    /**
     * Adds a line to the end of a hologram.
     *
     * @param text text
     * @return line
     */
    @Override
    public HologramBuilder addLine(String text) {
        if (text == null)
            throw new IllegalArgumentException("Text cannot be null!");
        this.lines.add(text);
        return this;
    }

    /**
     * Removes the line at the position.
     *
     * @param position position
     * @return instance
     */
    @Override
    public HologramBuilder removeLine(int position) {
        if (position >= this.lines.size() || position < 0)
            return this;
        this.lines.remove(position);
        return this;
    }

    /**
     * Removes all matching lines from the hologram.
     *
     * @param text
     * @return instance
     */
    @Override
    public HologramBuilder removeLine(String text) {
        if (text == null)
            throw new IllegalArgumentException("Text cannot be null!");
        this.lines.remove(text);
        return this;
    }

    /**
     * Sets a text at the given position.
     *
     * @param position position
     * @param text     text
     * @return instance
     */
    @Override
    public HologramBuilder setLine(int position, String text) {
        if (text == null)
            throw new IllegalArgumentException("Text cannot be null!");
        this.lines.add(position, text);
        return this;
    }

    /**
     * Returns a line at the given position.
     *
     * @param position position
     * @return line
     */
    @Override
    public Optional<String> getLine(int position) {
        if (position >= this.lines.size() || position < 0)
            return Optional.empty();
        return Optional.of(this.lines.get(position));
    }

    /**
     * Returns all lines in an unmodifiable list.
     *
     * @return lines
     */
    @Override
    public List<String> getLines() {
        return Collections.unmodifiableList(this.lines);
    }

    /**
     * Returns the location of the hologram.
     *
     * @return location
     */
    @Override
    public Optional<Object> getLocation() {
        if (this.location == null) {
            return Optional.empty();
        }
        return Optional.of(this.location.clone());
    }

    /**
     * Sets the location of the hologram
     *
     * @param location location
     * @return instance
     */
    @Override
    public HologramBuilder setLocation(Object location) {
        this.location = ((Location) location).clone();
        return this;
    }

    /**
     * Serializes this data
     *
     * @return serializedContent
     */
    @Override
    public Map<String, Object> serialize() {
        final Map<String, Object> data = new LinkedHashMap<>();
        data.put("location", this.location.serialize());
        data.put("lines", this.lines.toArray(new String[this.lines.size()]));
        return data;
    }
}