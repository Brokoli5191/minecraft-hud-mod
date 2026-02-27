# VapeHUD Minecraft Mod

A Vape V4-style HUD overlay mod for **Minecraft 1.21.1** using Fabric.

## Features
- Toggle HUD with **Right Shift**
- Dark panel UI with red accents (Vape V4 style)
- Module system with categories (Combat, Movement, World)

## Modules
| Module | Category | Description |
|--------|----------|-------------|
| Killaura | Combat | Auto-attacks nearby entities |
| Sprint | Combat | Auto-sprints when moving forward |
| NoFall | Combat | Sends ground packet to cancel fall damage |
| Scaffold | World | Auto-places blocks beneath you |
| Speed | Movement | Applies Speed V effect |

## Setup
1. Download [Fabric Loader](https://fabricmc.net/use/) for MC 1.21.1
2. Clone this repo
3. Run `./gradlew runClient`

## Building
```bash
./gradlew build
```
Output JAR: `build/libs/vapehud-1.0.0.jar`
