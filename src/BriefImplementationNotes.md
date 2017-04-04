# Climatar Brief Implementation Notes
---
## Model
Quite simply, a `String` to `Object` map.

#### Model#get(String propertyId)
Returns the object given the propertyId.

#### Model#set(String propertyId, Object property):Object
Returns the object last object with this property (or null).

```java
Model m = new Model();
m.set("important-number", 3.14);
double importantNum = (double) m.get("important-number");
```

## View
Just extend `View` and implement the following methods properly:

1. **View#build(Group group)**
This is where you build your view. Add view components (`Actor`s) to the group.

2. **View#layout(float x, float y, float width, float height)**
    This is where you position your view components (`Actor`s). This gets called after:
    1. `View#build(Group group)`, 
    2. `View#setFrame(float x, float y, float width, float height)`, 
    3. and also after any window resizing happens.

3. **View#update(Model model)**
    Here is where you update your view's components with the given model.

4. **View#dispose()**
    Finally, dispose of any resources once the application is closed. (like textures, fonts, batches)

#### View#setFrame(float x, float y, float width, float height)
Sets the view to only draw to the specified region.

## Controllers
Controller Overview
```java
public class YourController extends Controller {
    private CustomView customView;
    
    @Override
    protected void layoutView() {
        showView(customView)
    }
    
    @Override
    protected void tick() {
        
    }
}
```
Two major points to note:

1. **No constructor**
    There was no constructor defined for `YourController`.
2. **Views were not initialized**
    `customView` was not initialized! This is done automatically.

#### Controller#layoutView()
This is called once, after the controller is set to start rendering.

#### Controller#tick()
This is called once every 30 seconds! (unless someone has changed the rendering FPS)

#### Controller#showView(View... views)
Shows all the views given, hiding any that weren't mentioned.

## ControllerManager
The controller manager is responsible for many other controllers! We only have one of these - `ApplicationController`. It has a `create()` method which is the entry point for every platform we deploy to. Please note that `ControllerManager`s:
1. Automatically initialize all declared `Controller` fields, and call their `tick()` method every frame.
2. Manage a list of controllers which can draw to the screen. Use `addViewController(controller)`/`removeViewController(controller)` to manage this list.

#### Example
```java
public class ApplicationController extends ControllerManager {
    
    // notice we don't initialize controller fields
	private YourController yourController;

	@Override
	public void create() {
		initialize(this); // this initializes all controller fields

		// in the `YourController` class, we had `showView(customView)`
		// that view won't be seen unless the controller is marked for viewing.
		
		// we mark yourController for viewing
		addViewController(yourController); 
		
		// now the customView is being rendered :)
	}
}
```

I hope this helps when reading through the code!