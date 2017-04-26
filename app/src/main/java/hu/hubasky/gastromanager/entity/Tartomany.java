package hu.hubasky.gastromanager.entity;

import android.content.res.TypedArray;

import hu.hubasky.gastromanager.IBuilder;

/**
 * Értéktartomány.
 * Created by mirso on 2017. 04. 26..
 */

public final class Tartomany {

    /**
     * Építő.
     */
    public static class Builder implements IBuilder<Tartomany> {

        private Double min;
        private Double max;

        /**
         * Az alsó értékhatár.
         *
         * @param val az érték.
         * @return az építő.
         */
        public Builder min(double val) {
            min = val;
            return this;
        }

        /**
         * A felső értékhatár.
         *
         * @param val az érték.
         * @return az építő.
         */
        public Builder max(double val) {
            max = val;
            return this;
        }

        @Override
        public hu.hubasky.gastromanager.entity.Tartomany build() {
            return new Tartomany(min, max);
        }
    }

    /**
     * Az alsó határ.
     */
    private final Double min;
    /**
     * A felső határ.
     */
    private final Double max;

    /**
     * Konstruktor.
     *
     * @param min az alsó határ. Lehet {@code null}.
     * @param max a felső határ. Lehet {@code null}.
     */
    private Tartomany(Double min, Double max) {
        this.min = min;
        this.max = max;
        if (min != null && max != null) {
            if (min > max) {
                throw new IllegalArgumentException(String.format("Érvénytelen tartomány: %.3f - %.3f", min, max));
            }
        }
    }

    /**
     * Megfelel-e az érték a tartománynak.
     *
     * @param val a vizsgált érték.
     * @return true, ha megfelel.
     */
    public boolean isMegfelel(double val) {
        if (min != null && val < min) {
            return false;
        }
        if (max != null && val > max) {
            return false;
        }
        return true;
    }

    /**
     * Visszaadja az alsó értékhatárt.
     *
     * @return az értékhatár vagy {@code null}, ha nincs ilyen.
     */
    public Double getMin() {
        return min;
    }

    /**
     * Visszaadja a felső értékhatárt.
     *
     * @return az értékhatár vagy {@code null}, ha nincs ilyen.
     */
    public Double getMax() {
        return max;
    }
}
