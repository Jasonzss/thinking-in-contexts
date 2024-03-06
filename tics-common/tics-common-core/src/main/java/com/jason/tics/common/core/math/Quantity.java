package com.jason.tics.common.core.math;

import com.jason.tics.common.core.utils.UnitUtil;
import com.jason.tics.common.core.utils.UnitUtil.Unit;
import org.apache.commons.lang3.math.Fraction;

/**
 * @author Jason
 *
 * 数量：数值+单位
 */
public class Quantity {
    private Fraction value;
    private Unit unit;

    public Quantity(Fraction value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public Quantity(String quantity) {
        String[] a = quantity.split(" ");
        value = Fraction.getFraction(a[0]);
        unit = UnitUtil.getUnit(a[1]);
    }

    public Fraction getValue() {
        return value;
    }

    public Fraction getValue(Unit unit){
        return this.unit.getRate().multiplyBy(value);
    }

    public void setValue(Fraction value) {
        this.value = value;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Quantity unitConvert(Unit unit){
        Fraction v = this.unit.getRate().multiplyBy(value);
        return new Quantity(v, unit);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Quantity){
            Quantity o = (Quantity) obj;
            return UnitUtil.conversion(value, unit, o.getUnit()).compareTo(o.getValue()) == 0;
        }

        return false;
    }

    @Override
    public String toString() {
        return value.toString()+" "+unit.getUnit();
    }
}
