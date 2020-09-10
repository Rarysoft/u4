package com.rarysoft.u4.model;

import java.util.stream.Stream;

import static com.rarysoft.u4.model.TileType.*;

public enum Tile {
    DEEP_WATER(0, GROUND, 0),
    MEDIUM_WATER(1, GROUND, 0),
    SHALLOW_WATER(2, GROUND, 0),
    SWAMP(3, GROUND, 90),
    GRASSLANDS(4, GROUND, 100),
    SCRUBLAND(5, GROUND, 60),
    FOREST(6, GROUND, 78),
    HILLS(7, GROUND, 50),
    MOUNTAINS(8, GROUND, 0),
    DUNGEON_ENTRANCE(9, PORTAL, 100),
    TOWN(10, PORTAL, 100),
    CASTLE(11, PORTAL, 100),
    VILLAGE(12, PORTAL, 100),
    LORD_BRITISHS_CASTLE_WEST(13, GROUND, 0),
    LORD_BRITISHS_CASTLE_ENTRANCE(14, PORTAL, 100),
    LORD_BRITISHS_CASTLE_EAST(15, GROUND, 0),
    SHIP_WEST(16, TRANSPORT, 100),
    SHIP_NORTH(17, TRANSPORT, 100),
    SHIP_EAST(18, TRANSPORT, 100),
    SHIP_SOUTH(19, TRANSPORT, 100),
    HORSE_WEST(20, TRANSPORT, 100),
    HORSE_EAST(21, TRANSPORT, 100),
    TILE_FLOOR(22, GROUND, 100),
    BRIDGE(23, GROUND, 100),
    BALLOON(24, TRANSPORT, 100),
    BRIDGE_NORTH(25, GROUND, 100),
    BRIDGE_SOUTH(26, GROUND, 100),
    LADDER_UP(27, PORTAL, 100),
    LADDER_DOWN(28, PORTAL, 100),
    RUINS(29, PORTAL, 100),
    SHRINE(30, PORTAL, 100),
    AVATAR(31, PLAYER, 0),
    MAGE_1(32, NPC, 0),
    MAGE_2(33, NPC, 0),
    BARD_1(34, NPC, 0),
    BARD_2(35, NPC, 0),
    FIGHTER_1(36, NPC, 0),
    FIGHTER_2(37, NPC, 0),
    DRUID_1(38, NPC, 0),
    DRUID_2(39, NPC, 0),
    TINKER_1(40, NPC, 0),
    TINKER_2(41, NPC, 0),
    PALADIN_1(42, NPC, 0),
    PALADIN_2(43, NPC, 0),
    RANGER_1(44, NPC, 0),
    RANGER_2(45, NPC, 0),
    SHEPHERD_1(46, NPC, 0),
    SHEPHERD_2(47, NPC, 0),
    COLUMN(48, OBSTACLE, 0),
    WHITE_SW(49, OBSTACLE, 0),
    WHITE_SE(50, OBSTACLE, 0),
    WHITE_NW(51, OBSTACLE, 0),
    WHITE_NE(52, OBSTACLE, 0),
    MAST(53, OBSTACLE, 0),
    SHIPS_WHEEL(54, OBSTACLE, 0),
    ROCKS(55, OBSTACLE, 0),
    LYIN_DOWN(56, OBSTACLE, 0),
    STONE_WALL(57, OBSTACLE, 0),
    LOCKED_DOOR(58, OBSTACLE, 0),
    UNLOCKED_DOOR(59, OBSTACLE, 0),
    CHEST(60, SPECIAL, 100),
    ANKH(61, OBSTACLE, 0),
    BRICK_FLOOR(62, GROUND, 100),
    WOODEN_PLANKS(63, GROUND, 100),
    MOONGATE_1(64, OBSTACLE, 0),
    MOONGATE_2(65, OBSTACLE, 0),
    MOONGATE_3(66, OBSTACLE, 0),
    MOONGATE_4(67, PORTAL, 100),
    POISON_FIELD(68, GROUND, 100),
    ENERGY_FIELD(69, GROUND, 100),
    FIRE_FIELD(70, GROUND, 100),
    SLEEP_FIELD(71, GROUND, 100),
    SOLID_BARRIER(72, OBSTACLE, 0),
    HIDDEN_PASSAGE(73, GROUND, 100),
    ALTAR(74, OBSTACLE, 0),
    SPIT(75, OBSTACLE, 0),
    MISSLE(77, SPECIAL, 0),
    MAGIC_SPHERE(78, SPECIAL, 0),
    ATTACK_FLASH(79, SPECIAL, 0),
    GUARD_1(80, NPC, 0),
    GUARD_2(81, NPC, 0),
    CITIZEN_1(82, NPC, 0),
    CITIZEN_2(83, NPC, 0),
    SINGING_BARD_1(84, NPC, 0),
    SINGING_BARD_2(85, NPC, 0),
    JESTER_1(86, NPC, 0),
    JESTER_2(87, NPC, 0),
    BEGGAR_1(88, NPC, 0),
    BEGGAR_2(89, NPC, 0),
    CHILD_1(90, NPC, 0),
    CHILD_2(91, NPC, 0),
    BULL_1(92, NPC, 0),
    BULL_2(93, NPC, 0),
    LORD_BRITISH_1(94, NPC, 0),
    LORD_BRITISH_2(95, NPC, 0),
    A(96, OBSTACLE, 0),
    B(97, OBSTACLE, 0),
    C(98, OBSTACLE, 0),
    D(99, OBSTACLE, 0),
    E(100, OBSTACLE, 0),
    F(101, OBSTACLE, 0),
    G(102, OBSTACLE, 0),
    H(103, OBSTACLE, 0),
    I(104, OBSTACLE, 0),
    J(105, OBSTACLE, 0),
    K(106, OBSTACLE, 0),
    L(107, OBSTACLE, 0),
    M(108, OBSTACLE, 0),
    N(109, OBSTACLE, 0),
    O(110, OBSTACLE, 0),
    P(111, OBSTACLE, 0),
    Q(112, OBSTACLE, 0),
    R(113, OBSTACLE, 0),
    S(114, OBSTACLE, 0),
    T(115, OBSTACLE, 0),
    U(116, OBSTACLE, 0),
    V(117, OBSTACLE, 0),
    W(118, OBSTACLE, 0),
    X(119, OBSTACLE, 0),
    Y(120, OBSTACLE, 0),
    Z(121, OBSTACLE, 0),
    SPACE(122, OBSTACLE, 0),
    RIGHT(123, OBSTACLE, 0),
    LEFT(124, OBSTACLE, 0),
    WINDOW(125, OBSTACLE, 0),
    BLANK(126, OBSTACLE, 0),
    BRICK_WALL(127, OBSTACLE, 0),
    PIRATE_SHIP_WEST(128, MONSTER, 0),
    PIRATE_SHIP_NORTH(129, MONSTER, 0),
    PIRATE_SHIP_EAST(130, MONSTER, 0),
    PIRATE_SHIP_SOUTH(131, MONSTER, 0),
    NIXIE_1(132, MONSTER, 0),
    NIXIE_2(133, MONSTER, 0),
    GIANT_SQUID_1(134, MONSTER, 0),
    GIANT_SQUID_2(135, MONSTER, 0),
    SEA_SERPENT_1(136, MONSTER, 0),
    SEA_SERPENT_2(137, MONSTER, 0),
    SEAHORSE_1(138, MONSTER, 0),
    SEAHORSE_2(139, MONSTER, 0),
    WHIRLPOOL_1(140, MONSTER, 0),
    WHIRLPOOL_2(141, MONSTER, 0),
    STORM_1(142, MONSTER, 0),
    STORM_2(143, MONSTER, 0),
    RAT_1(144, MONSTER, 0),
    RAT_2(145, MONSTER, 0),
    RAT_3(146, MONSTER, 0),
    RAT_4(147, MONSTER, 0),
    BAT_1(148, MONSTER, 0),
    BAT_2(149, MONSTER, 0),
    BAT_3(150, MONSTER, 0),
    BAT_4(151, MONSTER, 0),
    GIANT_SPIDER_1(152, MONSTER, 0),
    GIANT_SPIDER_2(153, MONSTER, 0),
    GIANT_SPIDER_3(154, MONSTER, 0),
    GIANT_SPIDER_4(155, MONSTER, 0),
    GHOST_1(156, MONSTER, 0),
    GHOST_2(157, MONSTER, 0),
    GHOST_3(158, MONSTER, 0),
    GHOST_4(159, MONSTER, 0),
    SLIME_1(160, MONSTER, 0),
    SLIME_2(161, MONSTER, 0),
    SLIME_3(162, MONSTER, 0),
    SLIME_4(163, MONSTER, 0),
    TROLL_1(164, MONSTER, 0),
    TROLL_2(165, MONSTER, 0),
    TROLL_3(166, MONSTER, 0),
    TROLL_4(167, MONSTER, 0),
    GREMLIN_1(168, MONSTER, 0),
    GREMLIN_2(169, MONSTER, 0),
    GREMLIN_3(170, MONSTER, 0),
    GREMLIN_4(171, MONSTER, 0),
    MIMIC_1(172, MONSTER, 0),
    MIMIC_2(173, MONSTER, 0),
    MIMIC_3(174, MONSTER, 0),
    MIMIC_4(175, MONSTER, 0),
    REAPER_1(176, MONSTER, 0),
    REAPER_2(177, MONSTER, 0),
    REAPER_3(178, MONSTER, 0),
    REAPER_4(179, MONSTER, 0),
    INSECT_SWARM_1(180, MONSTER, 0),
    INSECT_SWARM_2(181, MONSTER, 0),
    INSECT_SWARM_3(182, MONSTER, 0),
    INSECT_SWARM_4(183, MONSTER, 0),
    GAZER_1(184, MONSTER, 0),
    GAZER_2(185, MONSTER, 0),
    GAZER_3(186, MONSTER, 0),
    GAZER_4(187, MONSTER, 0),
    PHANTOM_1(188, MONSTER, 0),
    PHANTOM_2(189, MONSTER, 0),
    PHANTOM_3(190, MONSTER, 0),
    PHANTOM_4(191, MONSTER, 0),
    ORC_1(192, MONSTER, 0),
    ORC_2(193, MONSTER, 0),
    ORC_3(194, MONSTER, 0),
    ORC_4(195, MONSTER, 0),
    SKELETON_1(196, MONSTER, 0),
    SKELETON_2(197, MONSTER, 0),
    SKELETON_3(198, MONSTER, 0),
    SKELETON_4(199, MONSTER, 0),
    ROGUE_1(200, MONSTER, 0),
    ROGUE_2(201, MONSTER, 0),
    ROGUE_3(202, MONSTER, 0),
    ROGUE_4(203, MONSTER, 0),
    PYTHON_1(204, MONSTER, 0),
    PYTHON_2(205, MONSTER, 0),
    PYTHON_3(206, MONSTER, 0),
    PYTHON_4(207, MONSTER, 0),
    ETTIN_1(208, MONSTER, 0),
    ETTIN_2(209, MONSTER, 0),
    ETTIN_3(210, MONSTER, 0),
    ETTIN_4(211, MONSTER, 0),
    HEADLESS_1(212, MONSTER, 0),
    HEADLESS_2(213, MONSTER, 0),
    HEADLESS_3(214, MONSTER, 0),
    HEADLESS_4(215, MONSTER, 0),
    CYCLOPS_1(216, MONSTER, 0),
    CYCLOPS_2(217, MONSTER, 0),
    CYCLOPS_3(218, MONSTER, 0),
    CYCLOPS_4(219, MONSTER, 0),
    WISP_1(220, MONSTER, 0),
    WISP_2(221, MONSTER, 0),
    WISP_3(222, MONSTER, 0),
    WISP_4(223, MONSTER, 0),
    EVIL_MAGE_1(224, MONSTER, 0),
    EVIL_MAGE_2(225, MONSTER, 0),
    EVIL_MAGE_3(226, MONSTER, 0),
    EVIL_MAGE_4(227, MONSTER, 0),
    LICH_1(228, MONSTER, 0),
    LICH_2(229, MONSTER, 0),
    LICH_3(230, MONSTER, 0),
    LICH_4(231, MONSTER, 0),
    LAVA_LIZARD_1(232, MONSTER, 0),
    LAVA_LIZARD_2(233, MONSTER, 0),
    LAVA_LIZARD_3(234, MONSTER, 0),
    LAVA_LIZARD_4(235, MONSTER, 0),
    ZORN_1(236, MONSTER, 0),
    ZORN_2(237, MONSTER, 0),
    ZORN_3(238, MONSTER, 0),
    ZORN_4(239, MONSTER, 0),
    DAEMON_1(240, MONSTER, 0),
    DAEMON_2(241, MONSTER, 0),
    DAEMON_3(242, MONSTER, 0),
    DAEMON_4(243, MONSTER, 0),
    HYDRA_1(244, MONSTER, 0),
    HYDRA_2(245, MONSTER, 0),
    HYDRA_3(246, MONSTER, 0),
    HYDRA_4(247, MONSTER, 0),
    DRAGON_1(248, MONSTER, 0),
    DRAGON_2(249, MONSTER, 0),
    DRAGON_3(250, MONSTER, 0),
    DRAGON_4(251, MONSTER, 0),
    BALRON_1(252, MONSTER, 0),
    BALRON_2(253, MONSTER, 0),
    BALRON_3(254, MONSTER, 0),
    BALRON_4(255, MONSTER, 0);

    private int index;

    private TileType type;

    private int walkability;

    Tile(int index, TileType type, int walkability) {
        this.index = index;
        this.type = type;
        this.walkability = walkability;
    }

    public static Tile forIndex(int index) {
        return Stream.of(values()).filter(tile -> tile.index() == index).findAny().orElseThrow(IllegalArgumentException::new);
    }

    public int index() {
        return index;
    }

    public TileType type() {
        return type;
    }

    public int walkability() {
        return walkability;
    }
}
