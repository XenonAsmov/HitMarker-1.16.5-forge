# HitMarker-1.16.5-forge

This repository contains the source code for the **HitMarker** mod for **Minecraft Forge 1.16.5**. The mod adds a hit marker for players and works with a **snapshot version of the mappings** `version: '20210309-1.16.5'`.



1. **Install Minecraft and Forge**:
   - Download and install **Minecraft** (version 1.16.5).
   - Download **Minecraft Forge 1.16.5** from the official website: [Minecraft Forge](https://files.minecraftforge.net/net/minecraftforge/forge/index_1.16.5.html).
   - Make sure you download **Forge for version 1.16.5** (not for any newer version).

2. **Set Up Your Development Environment** (If you want to compile the mod):
   - Install **IntelliJ IDEA** or **Eclipse**.
   - Open the project in your IDE.
   - Run `gradlew setupDecompWorkspace` to set up the workspace.

3. **Build and Install the Mod**:
   - If you want to build the mod, open your terminal and run the following command:
     ```bash
     gradlew build
     ```
   - After the build finishes, go to the `build/libs` folder and copy the `.jar` file into your Minecraft `mods` folder.

4. **Launch Minecraft**:
   - Open Minecraft via the **Minecraft Launcher**.
   - Select the profile with **Forge** installed.
   - The **HitMarker** mod will now be active.

> **Important**: This mod uses **snapshot mappings** for Forge version `20210309-1.16.5`, meaning it may not be compatible with the official Forge version.

## Features

- **Toggle Control**:
  - Press **F7** to enable the hit marker.
  - Press **F8** to disable the hit marker.

## Screenshot

Here’s a preview of the HitMarker mod in action:

![HitMarker Preview](https://cdn.discordapp.com/attachments/1323239804502020147/1323313494187053116/image.png?ex=67740f3a&is=6772bdba&hm=8b03b915882d86c2a42900c8dc242a2114d2f9ee42b0e49388704233069d5684)
