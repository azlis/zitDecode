package com.webdao.model;

/* loaded from: classes.dex */
public class ValueCombined
{
    public float _max;
    public float _min;
    public float _value;
    public boolean isFloat;

    public ValueCombined(float min, float max, float v)
    {
        this._max = max;
        this._min = min;
        this._value = v;
        if (((double) this._max) - Math.floor((double) this._max) > 0.0d || ((double) this._min) - Math.floor((double) this._min) > 0.0d)
        {
            this.isFloat = true;
        }
    }

    public ValueCombined(float min, float max, float v, boolean isFloat)
    {
        this._max = max;
        this._min = min;
        this._value = v;
        this.isFloat = isFloat;
    }

    public int getSeekMax()
    {
        if (this.isFloat)
        {
            return (int) (100.0f * (this._max - this._min));
        }
        return (int) (this._max - this._min);
    }

    public int getSeekMin()
    {
        if (this.isFloat)
        {
            return (int) (100.0f * (this._max - this._min));
        }
        return (int) (this._max - this._min);
    }

    public int getSeekValue()
    {
        if (this.isFloat)
        {
            return (int) (100.0f * (this._value - this._min));
        }
        return (int) (this._value - this._min);
    }

    public int getSeekValue(float v)
    {
        if (this.isFloat)
        {
            return (int) (100.0f * (v - this._min));
        }
        return (int) (v - this._min);
    }

    public float getValue(int v)
    {
        if (this.isFloat)
        {
            return (((float) v) / 100.0f) + this._min;
        }
        return ((float) v) + this._min;
    }
}
