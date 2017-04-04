package game.climatar.architecture;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import game.climatar.systems.ghg.GHGSystemModel;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AllowModel {
	
	Class<GHGSystemModel>[] value();
	
}
