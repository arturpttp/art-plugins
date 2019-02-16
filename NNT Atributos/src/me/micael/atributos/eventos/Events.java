package me.micael.atributos.eventos;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageModifier;
import org.bukkit.event.player.PlayerJoinEvent;

import me.micael.atributos.manager.AtributosAPI;

public class Events implements Listener {

	public static Map<String, Double> lifes = new HashMap<>();

	@EventHandler
	public void aobater(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			if (e.getEntity() instanceof LivingEntity) {
				Player p = (Player) e.getDamager();
				int forca = AtributosAPI.pegar_forca(p.getName());

				double damage = e.getDamage(DamageModifier.BASE) + forca * 2;

				e.setDamage(damage);

				int i = 2 + new Random().nextInt(5);
				int i2 = 2 + new Random().nextInt(5);
				int i3 = 2 + new Random().nextInt(5);

				AtributosAPI.setaratributos(p.getName(), AtributosAPI.pegar_forca(p.getName()) + i,
						AtributosAPI.pegar_vida(p.getName()) + i2, AtributosAPI.pegar_defesa(p.getName()) + i3);
			}
		}
	}

	@EventHandler
	public void aoentrar(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (p.hasPlayedBefore()) {
			p.setMaxHealth(20);
		} else {
			AtributosAPI.criar_conta(p.getName(), 350, 350, 350);
			p.setMaxHealth(20);
		}
	}
}