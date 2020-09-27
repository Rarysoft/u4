# U4

## Description

This project aims to re-implement the classic game Ultima IV - Quest of the Avatar, based on the original game data
files, but adding some enhancements.

## Justification

Why do this? Simply put, it's a hobby project, which is to say that I'm doing it because I want to.

## History

I was reading about Ultima IV online and came across an article that described the
[layout of the game data files](https://wiki.ultimacodex.com/wiki/Ultima_IV_internal_formats), and I decided to
create a map viewer application, so I could see all of Britannia as it was in Ultima IV. There are existing map viewers
out there already, but again, this is a hobby project. It's not just about having a map viewer, it's about building it.
As I was building the viewer, I kept adding to it: first animated tiles, then non-player characters, then moongates.
Eventually I decided to transfer that code over to a new project to actually implement the game itself.

## Data Files

This project uses the original data files, which are not included in the source code repository due to their being
proprietary copyright-protected material. Anyone who wants to run this version of Ultima IV will need to copy those
files from their own original Ultima IV game disk into the `src/main/resources/data` directory.

## Enhancements

* Increase visible area (21x21) and make it circular around the player for more realism
* More realistic blocking of visibility by terrain and objects
* Entry to settlements by simply walking onto the tile
* Animation of the water portion of the half white / half water tiles
* Apply transparency to characters to see the ground tile below them
* Apply transparency to some object tiles and guess at the correct ground tile to show below them
* more to come...
