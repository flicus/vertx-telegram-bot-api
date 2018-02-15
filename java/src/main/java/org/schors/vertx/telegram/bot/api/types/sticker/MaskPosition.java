package org.schors.vertx.telegram.bot.api.types.sticker;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MaskPosition {

    private String point;
    @JsonProperty("x_shift")
    private Float xShift;
    @JsonProperty("y_shift")
    private Float yShift;
    private Float scale;

    public MaskPosition() {
    }

    public MaskPosition(String point, Float xShift, Float yShift, Float scale) {
        this.point = point;
        this.xShift = xShift;
        this.yShift = yShift;
        this.scale = scale;
    }

    public String getPoint() {
        return point;
    }

    public MaskPosition setPoint(String point) {
        this.point = point;
        return this;
    }

    public Float getxShift() {
        return xShift;
    }

    public MaskPosition setxShift(Float xShift) {
        this.xShift = xShift;
        return this;
    }

    public Float getyShift() {
        return yShift;
    }

    public MaskPosition setyShift(Float yShift) {
        this.yShift = yShift;
        return this;
    }

    public Float getScale() {
        return scale;
    }

    public MaskPosition setScale(Float scale) {
        this.scale = scale;
        return this;
    }
}
