# 3D Rendering Engine Simulator

This project implements a 3D rendering engine. It includes essential components such as elements, geometries, primitives, a renderer, and scene management, enabling the creation and rendering of basic 3D scenes.

## Table of Contents

- [Features](#features)
- [Components](#components)
  - [Elements](#elements)
  - [Geometries](#geometries)
  - [Primitives](#primitives)
  - [Renderer](#renderer)
  - [Scene](#scene)
- [Getting Started](#getting-started)
- [Usage](#usage)

## Features

- **Scene Management**: Create and manage scenes with ambient light, backgrounds, geometries, and lights.
- **Geometries**: Define various geometric shapes for rendering.
- **Primitives**: Implement basic 3D primitives (spheres, cubes, etc.) for scene construction.
- **Rendering**: Render the 3D scene to create 2D images.
- **Extensibility**: Easily extend the engine with additional geometries and features.

## Components

### Elements

The `elements` package contains classes for representing lights and ambient light in the scene.

- **Light**: Represents point or directional lights affecting the scene.
- **AmbientLight**: Provides a uniform light source that illuminates all objects in the scene.

### Geometries

The `geometries` package includes classes for different geometric shapes.

- **Geometry**: An abstract class representing any geometric shape.
- Subclasses may include:
  - **Sphere**
  - **Plane**
  - **Triangle**

### Primitives

The `primitives` package provides basic mathematical and geometric constructs used in rendering.

- **Point3D**: Represents a point in 3D space.
- **Vector**: Represents a directional vector in 3D space.
- **Color**: Represents RGB color values.

### Renderer

The `renderer` package contains classes responsible for rendering the scene into a 2D image.

- **Renderer**: The core class responsible for rendering the scene.
- Methods to handle ray tracing and pixel color computation.

### Scene

The `scene` package manages the overall scene configuration.

- **Scene**: Represents a 3D scene containing elements like geometries, lights, and cameras.
- Methods for adding geometries and lights, and managing the camera view.

## Getting Started

To set up the project locally:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/3DRenderingEngine.git
   cd 3DRenderingEngine
    ```
2. **Build the project (if using a build tool like Maven or Gradle):

  ```bash
  mvn install
  ```

3. **Run the application:

  ```bash
  java -cp target/3DRenderingEngine.jar Main
  ```
## Usage
To create a simple scene, you can follow the example below:

```java
Copy code
// Create a scene
Scene scene = new Scene("My First Scene");

// Set background color
scene.set_background(new Color(100, 100, 100));

// Add ambient light
scene.set_ambientLight(new AmbientLight(new Color(255, 255, 255), 0.2));

// Create geometries and add to the scene
Geometry sphere = new Sphere(new Point3D(0, 0, -5), 1, new Color(255, 0, 0));
scene.addGeometry(sphere);

// Create a light source and add to the scene
Light light = new Light(new Point3D(5, 5, 0), new Color(255, 255, 255));
scene.addLight(light);

// Render the scene
Renderer renderer = new Renderer(scene);
renderer.render();
```
