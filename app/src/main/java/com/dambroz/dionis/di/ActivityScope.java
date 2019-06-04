package com.dambroz.dionis.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Определяет область использования Activity
 */
@Scope
@Retention(RetentionPolicy.CLASS)
public @interface ActivityScope {
}
