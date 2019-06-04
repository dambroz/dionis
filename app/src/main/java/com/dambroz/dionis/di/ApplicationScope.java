package com.dambroz.dionis.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Определяет область использования Application
 */
@Scope
@Retention(RetentionPolicy.CLASS)
public @interface ApplicationScope {
}
