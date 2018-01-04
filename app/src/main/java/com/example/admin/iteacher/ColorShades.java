package com.example.admin.iteacher;

import android.graphics.Color;


/**
 *
 * Ресурс: https://gist.github.com/cooltechworks/4f37021b1216f773daf8
 * Цветные оттенки обеспечивают все промежуточные цвета между двумя цветами. Это просто требует десятичного значения от 0.0 до 1.0
 * и обеспечивает точное сочетание оттенков двух цветов с этим значением оттенка.
 *
 *  Объяснение работы программы :
 *
 *      |===============|===============|===============|===============|
 *     Белый        Светло-Серый       Серый         Темно-Серый       Черный
 *
 *     0                0.25            0.5             0.75            1
 *
 * Учитываю два цвета: белый и черный,
 * и тени
 * пусть 0 дает белый
 * пусть 0,25 дает светло-серый
 * пусть 0,5 дает Gray
 * пусть 0,75 дает темно-серый
 * пусть 1 дает черный.
 */
public class ColorShades {

    private int mFromColor;
    private int mToColor;
    private float mShade;

    public ColorShades setFromColor(int fromColor) {
        mFromColor = fromColor;
        return this;
    }

    public ColorShades setToColor(int toColor) {
        mToColor = toColor;
        return this;
    }

    public ColorShades setShade(float shade) {
        mShade = shade;
        return this;
    }

    /**
     * Создает тень для заданного цвета.
     * @return возвращает значение тени
     */
    public int generate() {

        int fromR = (Color.red(mFromColor));
        int fromG = (Color.green(mFromColor));
        int fromB = (Color.blue(mFromColor));

        int toR = (Color.red(mToColor));
        int toG = (Color.green(mToColor));
        int toB = (Color.blue(mToColor));

        int diffR = toR - fromR;
        int diffG = toG - fromG;
        int diffB = toB - fromB;

        int R = fromR + (int) (( diffR * mShade));
        int G = fromG + (int) (( diffG * mShade));
        int B = fromB + (int) (( diffB * mShade));

        return  Color.rgb(R, G, B);
    }
}