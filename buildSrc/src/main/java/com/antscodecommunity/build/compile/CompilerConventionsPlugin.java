/*
 *  Copyright 2022-2022 AntsCodeCommunity
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.antscodecommunity.build.compile;

import org.gradle.api.JavaVersion;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.JavaPluginExtension;
import org.gradle.api.tasks.compile.JavaCompile;

import java.nio.charset.StandardCharsets;

/**
 * @author antscode
 */
public class CompilerConventionsPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		project.getPlugins().withType(JavaPlugin.class, javaPlugin -> {
			JavaPluginExtension javaPluginExtension = project.getExtensions().findByType(JavaPluginExtension.class);
			assert javaPluginExtension != null;
			javaPluginExtension.setSourceCompatibility(JavaVersion.VERSION_17);
			javaPluginExtension.setTargetCompatibility(JavaVersion.VERSION_17);
			project.getTasks().withType(JavaCompile.class).matching(javaCompile -> javaCompile.getName().equals(JavaPlugin.COMPILE_JAVA_TASK_NAME) || javaCompile.getName().equals(JavaPlugin.COMPILE_TEST_JAVA_TASK_NAME)).forEach(javaCompile -> javaCompile.getOptions().setEncoding(StandardCharsets.UTF_8.name()));
		});
	}

}
