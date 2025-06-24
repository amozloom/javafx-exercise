# 🎾 Bouncing Balls Lab (JavaFX Animation)

This is a JavaFX-based animation project created as part of an individual lab for the Software Design course at Elon University. The lab focuses on practicing shape animation, interaction, and event handling using JavaFX.

---

## 🧠 Lab Overview

The lab provided starter code with animated shapes and required several enhancements to deepen understanding of JavaFX functionality. The lab objectives were:

- Practice working with JavaFX `Shapes`, `ImageView`, and layout containers
- Handle keyboard and mouse input events
- Modify animation behavior and detect collisions
- Improve object scaling and movement logic

---

## ✅ Features Implemented

✔️ **Opposite Rotation**  
- The "grower" square now rotates **clockwise**, opposite the "mover."

✔️ **Mover Color Change on Collision**  
- When the mover intersects the grower, it changes to a **highlight color** (`OLIVEDRAB`).
- When no longer intersecting, it reverts to the original mover color (`PLUM`).

✔️ **Grower Collision Detection**  
- If **any blue bouncer image intersects** with the grower, the grower changes color.
- Color reverts when there are no longer collisions.

✔️ **Arrow Key Movement**  
- The mover can now move **up, down, left, and right** using the arrow keys.

✔️ **Square Scaling**  
- The grower now grows **uniformly** in both width and height when clicked, preserving its square shape.

---

## 🧪 Learning Outcomes

- Gained familiarity with JavaFX animation techniques
- Learned to use event listeners for mouse and keyboard input
- Practiced working with `Shape.intersect()`, collision logic, and bounding box detection
- Reinforced clean design through method-level abstraction and logical condition handling

---

## 👨‍💻 Author

**Anthony Mozloom**  
