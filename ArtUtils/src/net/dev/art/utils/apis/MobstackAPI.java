package net.dev.art.utils.apis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;

public class MobstackAPI {

	public static Map<Entity, Integer> stacks = new HashMap<>();

	public static void addToStack(LivingEntity e, int qnt) {
		stacks.put(e, qnt);
	}

	public static void addStack(LivingEntity entity, int stack) {
		addToStack(entity, getStack(entity) + stack);
	}

	public static void removeStack(LivingEntity entity, int stack) {
		addToStack(entity, getStack(entity) - stack);
	}

	public static int getStack(LivingEntity entity) {
		return stacks.getOrDefault(entity, 0);
	}

}
