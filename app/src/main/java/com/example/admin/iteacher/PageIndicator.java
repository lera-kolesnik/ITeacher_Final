/*
 * Copyright (C) 2011 Patrik Akerfeldt
 * Copyright (C) 2011 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.admin.iteacher;


import android.support.v4.view.ViewPager;


interface PageIndicator extends ViewPager.OnPageChangeListener {
    /**
     * Привязать индикатор к ViewPager.
     *
     * @param view
     */
    void setViewPager(ViewPager view);

    /**
     *
     * @param view
     * @param initialPosition
     */
    void setViewPager(ViewPager view, int initialPosition);

    /**
     * <p>Установить текущую страницу как ViewPager, так и индикатора.</p>
     *
     * <p>Это <strong>должно</strong> использоваться, если нужно установить страницу раньше
     *  (e.g., начальная страница).</p>
     *
     @param item
     */
    void setCurrentItem(int item);

    /**
     * Установка прослушивателя смены страницы, который будет получать переадресованные события.
     *
     * @param listener
     */
    void setOnPageChangeListener(ViewPager.OnPageChangeListener listener);

    /**
     * Уведомлять индикатор, который изменил список фрагментов.
     */
    void notifyDataSetChanged();
}
