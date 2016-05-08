# 3D-Render-Engine
A 3D renderer written completely from scratch in Java. No external libraries. No OpenGL.

Below depicts a progression of acheivements.
Note, this engine is soley for educational purposes in order to gain insight on how 3D engines like OpenGL work.

### Table of Contents
**[Line segments](#fun-with-line-segments)**  
**[Triangles](#fun-with-triangles)**  
**[Wireframes](#wireframes)**  
**[Shading](#shading)**  
**[Textures](#textures)**

## Fun with line segments ##
Only pixels who fit the equation of the line are drawn. Leads to holes in steep lines.
![Alt text](/home/yarden/workspace/3D Render Engine/res/examples/LineSegments1.png?raw=true "Primitive Method")

Aha! First check to see if the line is steep, and then draw the line. Much improvement.
![Alt text](/home/yarden/workspace/3D Render Engine/res/examples/LineSegments2.png?raw=true "Robust Method")

## Fun with triangles ##
Rasterizing triangles using barycentric coordinates.
![Alt text](/home/yarden/workspace/3D Render Engine/res/examples/TriangleRasterization.png?raw=true "Barycentric Coordinates")

## Wireframes ##
### Reading waveform .obj files###
__vertices__:
v -0.000581696 -0.734665 -0.623267

__normals__:
vn 0.001 0.482 -0.876

__textures__:
vt 0.532 0.923 0.000

__faces__:
f 24/1/24 25/2/25 26/3/26
(f v/vn/vt v/vn/vt v/vn/vt)

### Rendering ###

Draw a line segment for every two neighbouring vertices in the .obj file
![Alt text](/home/yarden/workspace/3D Render Engine/res/examples/WireFrame1.png?raw=true "Line Segments")

Draw a triangle represented by each face in the .obj file
![Alt text](/home/yarden/workspace/3D Render Engine/res/examples/WireFrame2.png?raw=true "Triangles")

Rasterize a triangle represented by each face in the .obj file
![Alt text](/home/yarden/workspace/3D Render Engine/res/examples/WireFrame3.png?raw=true "Rasterized Triangles")

### Shading ###
Flat shading: shade each polygon relative to the angle between the ploygon's normal vector and the direction of light
![Alt text](/home/yarden/workspace/3D Render Engine/res/examples/Shader1.png?raw=true "Flat Shading")

Flat shading with Z-Buffering: solves the visibility problem using a depth buffer
![Alt text](/home/yarden/workspace/3D Render Engine/res/examples/Shader2.png?raw=true "Z-Buffering")

### Textures ###
Direct uv-mapping. Each polygon filled with a solid colour from the diffused texture.
![Alt text](/home/yarden/workspace/3D Render Engine/res/examples/TextureShader1.png?raw=true "Texture Shading")

Interpolated uv-mapping. Each polygon filled with an interpolated colour from its vertices using barycentric coordinates.
![Alt text](/home/yarden/workspace/3D Render Engine/res/examples/TextureShader2.png?raw=true "Interpolating Shading")

Interpolated uv-mapping with lighting. Each polygon filled with an interpolated colour from its vertices using barycentric coordinates, and then multiplied by the light intensity.
![Alt text](/home/yarden/workspace/3D Render Engine/res/examples/TextureShader3.png?raw=true "Interpolating Shading (lighted)")


