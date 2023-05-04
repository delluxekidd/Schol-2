import pygame
import time
import json

from pygame.locals import*
from time import sleep

class Sprite():
	def __init__(self):
		self.real = True

class Link(Sprite):
	def __init__ (self):
		self.x = 100
		self.y = 100
		self.prevX = 100
		self.prevY = 100
		self.dir = 0
		self.w = 73
		self.h = 85
		self.right = self.x + self.w
		self.left = self.x
		self.top = self.y
		self.bottom = self.y + self.h
		self.pRight = self.x + self.w
		self.pLeft = self.x
		self.pTop = self.y
		self.pBottom = self.y + self.h
		self.index = 0
		self.images = []
		if self.images == []:
			#load all 50 images into the list
			for i in range(1,51):
				self.images.append(pygame.image.load("Paradigms/Paradigms HW 8/images/link" + str(i) + ".png"))
		self.image = self.images[0]

	def moveRight(self):
		self.x = self.x + 10
		self.index = self.index + 1
		self.dir = 2
		self.move(2)

	def moveLeft(self):
		self.x = self.x - 10
		self.index = self.index + 1
		self.dir = 1
		self.move(1)

	def moveUp(self):
		self.y = self.y - 10
		self.index = self.index + 1
		self.dir = 3
		self.move(3)

	def moveDown(self):
		self.y = self.y + 10
		self.index = self.index + 1
		self.dir = 0
		self.move(0)

	def move(self, dir):
		if dir == 0:
			if self.index > 10:
				self.index = 0
		if dir == 1:
			if self.index > 24 or self.index < 14:
				self.index = 14
		if dir == 2:
			if self.index > 37 or self.index < 27:
				self.index = 27
		if dir == 3:
			if self.index > 49 or self.index < 39:
				self.index = 39

	def updatePrev(self):
		self.pRight = self.x + self.w
		self.pLeft = self.x
		self.pTop = self.y
		self.pBottom = self.y + self.h

	def draw(self, surface, vx, vy):
		self.image = self.images[self.index]
		surface.blit(self.image, (self.x - vx, self.y - vy))

	def update(self):
		self.right = self.x + self.w
		self.left = self.x
		self.top = self.y
		self.bottom = self.y + self.h

	def isLink(self):
		return True

	def isPot(self):
		return False

	def isBoomerang(self):
		return False

	def isTile(self):
		return False

	def toString(self):
		#print out all the information about the link
		print("Link: " + str(self.x) + ", " + str(self.y) + ", " + str(self.right) + ", " + str(self.left) + ", " + str(self.top) + ", " + str(self.bottom))

class Boomerang(Sprite):
	def __init__(self, lx, ly, dir):
		self.x = lx
		self.y = ly
		self.w = 36
		self.h = 36
		self.right = self.x + self.w
		self.left = self.x
		self.top = self.y
		self.bottom = self.y + self.h
		#laod all four images into the list
		self.images = []
		if self.images == []:
			for i in range(1,5):
				self.images.append(pygame.image.load("Paradigms/Paradigms HW 8/images/boomerang" + str(i) + ".png"))
		self.image = self.images[0]
		self.dir = dir
		self.index = 0

	def moveRight(self):
		self.x = self.x + 15
		self.index = self.index + 1
		if self.index > 3:
			self.index = 0

	def moveLeft(self):
		self.x = self.x - 15
		self.index = self.index + 1
		if self.index > 3:
			self.index = 0

	def moveUp(self):
		self.y = self.y - 15
		self.index = self.index + 1
		if self.index > 3:
			self.index = 0

	def moveDown(self):
		self.y = self.y + 15
		self.index = self.index + 1
		if self.index > 3:
			self.index = 0

	def draw(self, surface, vx, vy):
		self.image = self.images[self.index]
		scaledImage = pygame.transform.scale(self.image, (self.w, self.h))
		surface.blit(scaledImage, (self.x - vx, self.y - vy))

	def isLink(self):
		return False

	def isPot(self):
		return False

	def isBoomerang(self):
		return True

	def isTile(self):
		return False

	def update(self):
		self.right = self.x + self.w
		self.left = self.x
		self.top = self.y
		self.bottom = self.y + self.h
		if self.dir == 0:
			self.moveDown()
		if self.dir == 1:
			self.moveLeft()
		if self.dir == 2:
			self.moveRight()
		if self.dir == 3:
			self.moveUp()

class Pot(Sprite):
	def __init__(self, x, y):
		self.x = x
		self.y = y
		self.ydir = 0
		self.xdir = 0
		self.frames = 0
		self.isBroken = False
		self.w = 50
		self.h = 50
		self.right = self.x + self.w
		self.left = self.x
		self.top = self.y
		self.bottom = self.y + self.h
		self.potImage = pygame.image.load("Paradigms/Paradigms HW 8/images/pot.png")
		self.brokenImage = pygame.image.load("Paradigms/Paradigms HW 8/images/pot_broken.png")

	def draw(self, surface, vx, vy):
		if self.isBroken == False:
			surface.blit(self.potImage, (self.x - vx, self.y - vy))
		else:
			surface.blit(self.brokenImage, (self.x - vx, self.y - vy))

	def isLink(self):
		return False

	def isPot(self):
		return True

	def isBoomerang(self):
		return False

	def isTile(self):
		return False

	def update(self):
		if self.isBroken == True:
			self.xdir = 0
			self.ydir = 0
			self.frames = self.frames + 1
		self.x = self.x + self.xdir
		self.y = self.y + self.ydir
		self.right = self.x + self.w
		self.left = self.x
		self.top = self.y
		self.bottom = self.y + self.h

	def breakPot(self):
		self.isBroken = True
		self.xdir = 0
		self.ydir = 0

	def removePot(self):
		return self.frames > 100

	def collided(self, dir):
		if dir == 0:
			self.ydir = 10
		if dir == 1:
			self.xdir = -10
		if dir == 2:
			self.xdir = 10
		if dir == 3:
			self.ydir = -10

class Tile(Sprite):
	def __init__(self, x, y):
		self.x = x
		self.y = y
		self.w = 50
		self.h = 50
		self.right = self.x + self.w
		self.left = self.x
		self.top = self.y
		self.bottom = self.y + self.h
		self.image = pygame.image.load("Paradigms/Paradigms HW 8/images/tile.png")

	def draw(self, surface, vx, vy):
		surface.blit(self.image, (self.x - vx, self.y - vy))

	def isLink(self):
		return False

	def isPot(self):
		return False

	def isBoomerang(self):
		return False

	def isTile(self):
		return True

	def update(self):
		#function does nothing
		return

class Model():
	def __init__(self):
		self.sprites = []
		self.spritesToBeRemoved = []
		self.link = Link()
		self.sprites.append(self.link)
		#read in the map from map.json
		self.map = json.load(open("Paradigms/Paradigms HW 8/map.json"))
		#loop through entire json file and extract all of the pots and tiles
		for i in self.map.values():
			for j in i:
				if j["type"] == "pot":
					self.sprites.append(Pot(j["x"], j["y"]))
				if j["type"] == "tile":
					self.sprites.append(Tile(j["x"], j["y"]))

	def shoot(self):
		self.sprites.append(Boomerang(self.link.x + 20, self.link.y + 20, self.link.dir))

	def snapToTile(self, t) -> None:
		if (self.link.right > t.left) & (self.link.pRight <= t.left) & (self.link.right < t.right):
			self.link.x = t.left - self.link.w

		if (self.link.left < t.right) & (self.link.pLeft >= t.right) & (self.link.left > t.left):
			self.link.x = t.right

		if (self.link.bottom > t.top) & (self.link.pBottom <= t.top) & (self.link.bottom < t.bottom):
			self.link.y = t.top - self.link.h

		if (self.link.top < t.bottom) & (self.link.pTop >= t.bottom) & (self.link.top > t.top):
			self.link.y = t.bottom

	def collision(self, a, b) -> None:
		if (a.right > b.left) and (a.left < b.right) and (a.bottom > b.top) and (a.top < b.bottom):
			if a.isLink() & b.isTile():
				self.snapToTile(b)

			elif a.isTile() & b.isLink():
				self.snapToTile(a)

			elif a.isBoomerang():
				if b.isTile():
					self.spritesToBeRemoved.append(a)
					return
				elif b.isPot():
					self.spritesToBeRemoved.append(a)
					b.breakPot()
					return

			elif b.isBoomerang():
				if a.isTile():
					self.spritesToBeRemoved.append(b)
					return
				elif a.isPot():
					self.spritesToBeRemoved.append(b)
					a.breakPot()
					return

			elif a.isPot():
				if b.isTile():
					a.breakPot()
					return
				elif b.isLink():
					a.collided(self.link.dir)
					return

			elif b.isPot():
				if a.isTile():
					b.breakPot()
				elif a.isLink():
					b.collided(self.link.dir)

	def update(self):
		for i in self.sprites:
			i.update()
			if i.isPot():
				if i.removePot():
					self.spritesToBeRemoved.append(i)
			for j in self.sprites:
				self.collision(j, i)
		if self.spritesToBeRemoved != []:
			for it in self.spritesToBeRemoved:
				try:
					self.sprites.remove(it)
				except:
					pass
			self.spritesToBeRemoved.clear()

class View():
	def __init__(self, model):
		screen_size = (1000,1000)
		self.screen = pygame.display.set_mode(screen_size, 32)
		self.model = model
		self.x = 0
		self.y = 0

	def update(self):
		self.screen.fill([0,0,0])
		#run through every sprite in model.sprites and call its draw function
		for sprite in self.model.sprites:
			sprite.draw(self.screen, self.x, self.y)
		pygame.display.flip()

	def moveRight(self):
		self.x = 1000

	def moveLeft(self):
		self.x = 0

	def moveUp(self):
		self.y = 0

	def moveDown(self):
		self.y = 1000

class Controller():
	def __init__(self, model, view):
		self.model = model
		self.view = view
		self.keep_going = True
		self.ctrl_pressed = False

	def update(self):
		for event in pygame.event.get():
			if event.type == QUIT:
				self.keep_going = False
			elif event.type == KEYDOWN:
				if event.key == K_ESCAPE:
					self.keep_going = False
				elif event.key == K_LCTRL and not self.ctrl_pressed:
					self.model.shoot()
					self.ctrl_pressed = True
			elif event.type == KEYUP:
				if event.key == K_LCTRL:
					self.ctrl_pressed = False
		keys = pygame.key.get_pressed()
		self.model.link.updatePrev()
		if self.model.link.x > 1000:
			self.view.moveRight()
		if self.model.link.x < 1000:
			self.view.moveLeft()
		if self.model.link.y > 1000:
			self.view.moveDown()
		if self.model.link.y < 1000:
			self.view.moveUp()
		if keys[K_LEFT]:
			self.model.link.moveLeft()
		if keys[K_RIGHT]:
			self.model.link.moveRight()
		if keys[K_UP]:
			self.model.link.moveUp()
		if keys[K_DOWN]:
			self.model.link.moveDown()

print("Use the arrow keys to move. Press Esc to quit.")
pygame.init()
m = Model()
v = View(m)
c = Controller(m, v)
while c.keep_going:
	c.update()
	m.update()
	v.update()
	sleep(0.04)
print("Goodbye")