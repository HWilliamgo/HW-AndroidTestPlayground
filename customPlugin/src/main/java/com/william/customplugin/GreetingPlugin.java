package com.william.customplugin;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

/**
 * date: 2019/9/6 0006
 *
 * @author hwj
 * description 插件
 */
public class GreetingPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.task("hello", new Action<Task>() {
            @Override
            public void execute(Task task) {
                task.doLast(new Action<Task>() {
                    @Override
                    public void execute(Task task) {
                        System.out.println("task hello---doLast");
                    }
                });
            }
        });
    }
}
