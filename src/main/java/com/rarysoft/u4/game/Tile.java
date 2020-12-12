package com.rarysoft.u4.game;

import java.util.stream.Stream;

public enum Tile {
    DEEP_WATER(0, false, 0, true, false, false, false, false),
    MEDIUM_WATER(1, false, 0, true, false, false, false, false),
    SHALLOW_WATER(2, false, 0, true, false, false, false, false),
    SWAMP(3, false, 90, false, false, false, false, false),
    GRASSLANDS(4, false, 100, true, false, false, false, false),
    SCRUBLAND(5, false, 60, true, false, false, false, false),
    FOREST(6, false, 78, true, false, true, false, false),
    HILLS(7, false, 50, true, false, false, false, false),
    MOUNTAINS(8, false, 0, true, false, true, false, false),
    DUNGEON_ENTRANCE(9, false, 100, true, true, false, false, false),
    TOWN(10, false, 100, true, true, false, false, false),
    CASTLE(11, false, 100, true, true, false, false, false),
    VILLAGE(12, false, 100, true, true, false , false, false),
    CASTLE_BRITANNIA_WEST(13, false, 0, false, false, false, false, false),
    CASTLE_BRITANNIA_ENTRANCE(14, false, 100, true, true, false, false, false),
    CASTLE_BRITANNIA_EAST(15, false, 0, false, false, false, false, false),
    SHIP_WEST(16, false, 100, true, false, false, false, false),
    SHIP_NORTH(17, false, 100, true, false, false, false, false),
    SHIP_EAST(18, false, 100, true, false, false, false, false),
    SHIP_SOUTH(19, false, 100, true, false, false, false, false),
    HORSE_WEST(20, false, 100, true, false, false, false, false),
    HORSE_EAST(21, false, 100, true, false, false, false, false),
    TILE_FLOOR(22, false, 100, true, false, false, false, false),
    BRIDGE(23, false, 100, true, false, false, false, false),
    BALLOON(24, false, 100, true, false, false, false, false),
    BRIDGE_NORTH(25, false, 100, true, false, false, false, false),
    BRIDGE_SOUTH(26, false, 100, true, false, false, false, false),
    LADDER_UP(27, false, 100, true, true, false, false, false),
    LADDER_DOWN(28, false, 100, true, true, false, false, false),
    RUINS(29, false, 100, true, true, false, false, false),
    SHRINE(30, false, 100, true, true, false, false, false),
    AVATAR(31, true, 0, false, false, false, false, false),
    MAGE_1(32, true, 0, false, false, false, false, false),
    MAGE_2(33, true, 0, false, false, false, false, false),
    BARD_1(34, true, 0, false, false, false, false, false),
    BARD_2(35, true, 0, false, false, false, false, false),
    FIGHTER_1(36, true, 0, false, false, false, false, false),
    FIGHTER_2(37, true, 0, false, false, false, false, false),
    DRUID_1(38, true, 0, false, false, false, false, false),
    DRUID_2(39, true, 0, false, false, false, false, false),
    TINKER_1(40, true, 0, false, false, false, false, false),
    TINKER_2(41, true, 0, false, false, false, false, false),
    PALADIN_1(42, true, 0, false, false, false, false, false),
    PALADIN_2(43, true, 0, false, false, false, false, false),
    RANGER_1(44, true, 0, false, false, false, false, false),
    RANGER_2(45, true, 0, false, false, false, false, false),
    SHEPHERD_1(46, true, 0, false, false, false, false, false),
    SHEPHERD_2(47, true, 0, false, false, false, false, false),
    COLUMN(48, false, 0, false, false, false, false, false),
    WHITE_SW(49, true, 0, false, false, false, false, true),
    WHITE_SE(50, true, 0, false, false, false, false, true),
    WHITE_NW(51, true, 0, false, false, false, false, true),
    WHITE_NE(52, true, 0, false, false, false, false, true),
    MAST(53, false, 0, false, false, false, false, false),
    SHIPS_WHEEL(54, false, 0, false, false, false, false, false),
    ROCKS(55, false, 0, false, false, false, false, false),
    LYIN_DOWN(56, true, 0, false, false, false, false, true),
    STONE_WALL(57, false, 0, false, false, true, false, false),
    LOCKED_DOOR(58, false, 0, false, false, false, false, false),
    UNLOCKED_DOOR(59, false, 0, false, false, false, false, false),
    CHEST(60, true, 100, false, false, false, false, true),
    ANKH(61, true, 0, false, false, false, false, true),
    BRICK_FLOOR(62, false, 100, true, false, false, false, false),
    WOODEN_PLANKS(63, false, 100, true, false, false, false, false),
    MOONGATE_1(64, false, 0, false, false, false, false, false),
    MOONGATE_2(65, false, 0, false, false, false, false, false),
    MOONGATE_3(66, false, 0, false, false, false, false, false),
    MOONGATE_4(67, false, 100, false, true, false, false, false),
    POISON_FIELD(68, false, 100, true, false, false, false, false),
    ENERGY_FIELD(69, false, 0, true, false, false, false, false),
    FIRE_FIELD(70, false, 100, true, false, false, false, false),
    SLEEP_FIELD(71, false, 100, true, false, false, false, false),
    SOLID_BARRIER(72, false, 0, false, false, false, false, false),
    HIDDEN_PASSAGE(73, false, 100, false, false, true, false, false),
    ALTAR(74, false, 0, false, false, false, false, false),
    SPIT(75, false, 0, false, false, false, false, false),
    LAVA_FLOW(76, false, 0, false, false, false, false, false),
    MISSILE(77, true, 0, false, false, false, false, false),
    MAGIC_SPHERE(78, true, 0, false, false, false, false, false),
    ATTACK_FLASH(79, true, 0, false, false, false, false, false),
    GUARD_1(80, true, 0, false, false, false, false, false),
    GUARD_2(81, true, 0, false, false, false, false, false),
    CITIZEN_1(82, true, 0, false, false, false, false, false),
    CITIZEN_2(83, true, 0, false, false, false, false, false),
    SINGING_BARD_1(84, true, 0, false, false, false, false, false),
    SINGING_BARD_2(85, true, 0, false, false, false, false, false),
    JESTER_1(86, true, 0, false, false, false, false, false),
    JESTER_2(87, true, 0, false, false, false, false, false),
    BEGGAR_1(88, true, 0, false, false, false, false, false),
    BEGGAR_2(89, true, 0, false, false, false, false, false),
    CHILD_2(91, true, 0, false, false, false, false, false),
    CHILD_1(90, true, 0, false, false, false, false, false),
    BULL_1(92, true, 0, false, false, false, false, false),
    BULL_2(93, true, 0, false, false, false, false, false),
    LORD_BRITISH_1(94, true, 0, false, false, false, false, false),
    LORD_BRITISH_2(95, true, 0, false, false, false, false, false),
    A(96, false, 0, false, false, false, true, false),
    B(97, false, 0, false, false, false, true, false),
    C(98, false, 0, false, false, false, true, false),
    D(99, false, 0, false, false, false, true, false),
    E(100, false, 0, false, false, false, true, false),
    F(101, false, 0, false, false, false, true, false),
    G(102, false, 0, false, false, false, true, false),
    H(103, false, 0, false, false, false, true, false),
    I(104, false, 0, false, false, false, true, false),
    J(105, false, 0, false, false, false, true, false),
    K(106, false, 0, false, false, false, true, false),
    L(107, false, 0, false, false, false, true, false),
    M(108, false, 0, false, false, false, true, false),
    N(109, false, 0, false, false, false, true, false),
    O(110, false, 0, false, false, false, true, false),
    P(111, false, 0, false, false, false, true, false),
    Q(112, false, 0, false, false, false, true, false),
    R(113, false, 0, false, false, false, true, false),
    S(114, false, 0, false, false, false, true, false),
    T(115, false, 0, false, false, false, true, false),
    U(116, false, 0, false, false, false, true, false),
    V(117, false, 0, false, false, false, true, false),
    W(118, false, 0, false, false, false, true, false),
    X(119, false, 0, false, false, false, true, false),
    Y(120, false, 0, false, false, false, true, false),
    Z(121, false, 0, false, false, false, true, false),
    SPACE(122, false, 0, false, false, false, true, false),
    RIGHT(123, false, 0, false, false, false, false, false),
    LEFT(124, false, 0, false, false, false, false, false),
    WINDOW(125, false, 0, false, false, false, false, false),
    BLANK(126, false, 0, false, false, false, false, false),
    BRICK_WALL(127, false, 0, false, false, true, false, false),
    PIRATE_SHIP_WEST(128, true, 0, false, false, false, false, false),
    PIRATE_SHIP_NORTH(129, true, 0, false, false, false, false, false),
    PIRATE_SHIP_EAST(130, true, 0, false, false, false, false, false),
    PIRATE_SHIP_SOUTH(131, true, 0, false, false, false, false, false),
    NIXIE_1(132, true, 0, false, false, false, false, false),
    NIXIE_2(133, true, 0, false, false, false, false, false),
    GIANT_SQUID_1(134, true, 0, false, false, false, false, false),
    GIANT_SQUID_2(135, true, 0, false, false, false, false, false),
    SEA_SERPENT_1(136, true, 0, false, false, false, false, false),
    SEA_SERPENT_2(137, true, 0, false, false, false, false, false),
    SEAHORSE_1(138, true, 0, false, false, false, false, false),
    SEAHORSE_2(139, true, 0, false, false, false, false, false),
    WHIRLPOOL_1(140, true, 0, false, false, false, false, false),
    WHIRLPOOL_2(141, true, 0, false, false, false, false, false),
    STORM_1(142, true, 0, false, false, false, false, false),
    STORM_2(143, true, 0, false, false, false, false, false),
    RAT_1(144, true, 0, false, false, false, false, false),
    RAT_2(145, true, 0, false, false, false, false, false),
    RAT_3(146, true, 0, false, false, false, false, false),
    RAT_4(147, true, 0, false, false, false, false, false),
    BAT_1(148, true, 0, false, false, false, false, false),
    BAT_2(149, true, 0, false, false, false, false, false),
    BAT_3(150, true, 0, false, false, false, false, false),
    BAT_4(151, true, 0, false, false, false, false, false),
    GIANT_SPIDER_1(152, true, 0, false, false, false, false, false),
    GIANT_SPIDER_2(153, true, 0, false, false, false, false, false),
    GIANT_SPIDER_3(154, true, 0, false, false, false, false, false),
    GIANT_SPIDER_4(155, true, 0, false, false, false, false, false),
    GHOST_1(156, true, 0, false, false, false, false, false),
    GHOST_2(157, true, 0, false, false, false, false, false),
    GHOST_3(158, true, 0, false, false, false, false, false),
    GHOST_4(159, true, 0, false, false, false, false, false),
    SLIME_1(160, true, 0, false, false, false, false, false),
    SLIME_2(161, true, 0, false, false, false, false, false),
    SLIME_3(162, true, 0, false, false, false, false, false),
    SLIME_4(163, true, 0, false, false, false, false, false),
    TROLL_1(164, true, 0, false, false, false, false, false),
    TROLL_2(165, true, 0, false, false, false, false, false),
    TROLL_3(166, true, 0, false, false, false, false, false),
    TROLL_4(167, true, 0, false, false, false, false, false),
    GREMLIN_1(168, true, 0, false, false, false, false, false),
    GREMLIN_2(169, true, 0, false, false, false, false, false),
    GREMLIN_3(170, true, 0, false, false, false, false, false),
    GREMLIN_4(171, true, 0, false, false, false, false, false),
    MIMIC_1(172, true, 0, false, false, false, false, false),
    MIMIC_2(173, true, 0, false, false, false, false, false),
    MIMIC_3(174, true, 0, false, false, false, false, false),
    MIMIC_4(175, true, 0, false, false, false, false, false),
    REAPER_1(176, true, 0, false, false, false, false, false),
    REAPER_2(177, true, 0, false, false, false, false, false),
    REAPER_3(178, true, 0, false, false, false, false, false),
    REAPER_4(179, true, 0, false, false, false, false, false),
    INSECT_SWARM_1(180, true, 0, false, false, false, false, false),
    INSECT_SWARM_2(181, true, 0, false, false, false, false, false),
    INSECT_SWARM_3(182, true, 0, false, false, false, false, false),
    INSECT_SWARM_4(183, true, 0, false, false, false, false, false),
    GAZER_1(184, true, 0, false, false, false, false, false),
    GAZER_2(185, true, 0, false, false, false, false, false),
    GAZER_3(186, true, 0, false, false, false, false, false),
    GAZER_4(187, true, 0, false, false, false, false, false),
    PHANTOM_1(188, true, 0, false, false, false, false, false),
    PHANTOM_2(189, true, 0, false, false, false, false, false),
    PHANTOM_3(190, true, 0, false, false, false, false, false),
    PHANTOM_4(191, true, 0, false, false, false, false, false),
    ORC_1(192, true, 0, false, false, false, false, false),
    ORC_2(193, true, 0, false, false, false, false, false),
    ORC_3(194, true, 0, false, false, false, false, false),
    ORC_4(195, true, 0, false, false, false, false, false),
    SKELETON_1(196, true, 0, false, false, false, false, false),
    SKELETON_2(197, true, 0, false, false, false, false, false),
    SKELETON_3(198, true, 0, false, false, false, false, false),
    SKELETON_4(199, true, 0, false, false, false, false, false),
    ROGUE_1(200, true, 0, false, false, false, false, false),
    ROGUE_2(201, true, 0, false, false, false, false, false),
    ROGUE_3(202, true, 0, false, false, false, false, false),
    ROGUE_4(203, true, 0, false, false, false, false, false),
    PYTHON_1(204, true, 0, false, false, false, false, false),
    PYTHON_2(205, true, 0, false, false, false, false, false),
    PYTHON_3(206, true, 0, false, false, false, false, false),
    PYTHON_4(207, true, 0, false, false, false, false, false),
    ETTIN_1(208, true, 0, false, false, false, false, false),
    ETTIN_2(209, true, 0, false, false, false, false, false),
    ETTIN_3(210, true, 0, false, false, false, false, false),
    ETTIN_4(211, true, 0, false, false, false, false, false),
    HEADLESS_1(212, true, 0, false, false, false, false, false),
    HEADLESS_2(213, true, 0, false, false, false, false, false),
    HEADLESS_3(214, true, 0, false, false, false, false, false),
    HEADLESS_4(215, true, 0, false, false, false, false, false),
    CYCLOPS_1(216, true, 0, false, false, false, false, false),
    CYCLOPS_2(217, true, 0, false, false, false, false, false),
    CYCLOPS_3(218, true, 0, false, false, false, false, false),
    CYCLOPS_4(219, true, 0, false, false, false, false, false),
    WISP_1(220, true, 0, false, false, false, false, false),
    WISP_2(221, true, 0, false, false, false, false, false),
    WISP_3(222, true, 0, false, false, false, false, false),
    WISP_4(223, true, 0, false, false, false, false, false),
    EVIL_MAGE_1(224, true, 0, false, false, false, false, false),
    EVIL_MAGE_2(225, true, 0, false, false, false, false, false),
    EVIL_MAGE_3(226, true, 0, false, false, false, false, false),
    EVIL_MAGE_4(227, true, 0, false, false, false, false, false),
    LICH_1(228, true, 0, false, false, false, false, false),
    LICH_2(229, true, 0, false, false, false, false, false),
    LICH_3(230, true, 0, false, false, false, false, false),
    LICH_4(231, true, 0, false, false, false, false, false),
    LAVA_LIZARD_1(232, true, 0, false, false, false, false, false),
    LAVA_LIZARD_2(233, true, 0, false, false, false, false, false),
    LAVA_LIZARD_3(234, true, 0, false, false, false, false, false),
    LAVA_LIZARD_4(235, true, 0, false, false, false, false, false),
    ZORN_1(236, true, 0, false, false, false, false, false),
    ZORN_2(237, true, 0, false, false, false, false, false),
    ZORN_3(238, true, 0, false, false, false, false, false),
    ZORN_4(239, true, 0, false, false, false, false, false),
    DAEMON_1(240, true, 0, false, false, false, false, false),
    DAEMON_2(241, true, 0, false, false, false, false, false),
    DAEMON_3(242, true, 0, false, false, false, false, false),
    DAEMON_4(243, true, 0, false, false, false, false, false),
    HYDRA_1(244, true, 0, false, false, false, false, false),
    HYDRA_2(245, true, 0, false, false, false, false, false),
    HYDRA_3(246, true, 0, false, false, false, false, false),
    HYDRA_4(247, true, 0, false, false, false, false, false),
    DRAGON_1(248, true, 0, false, false, false, false, false),
    DRAGON_2(249, true, 0, false, false, false, false, false),
    DRAGON_3(250, true, 0, false, false, false, false, false),
    DRAGON_4(251, true, 0, false, false, false, false, false),
    BALRON_1(252, true, 0, false, false, false, false, false),
    BALRON_2(253, true, 0, false, false, false, false, false),
    BALRON_3(254, true, 0, false, false, false, false, false),
    BALRON_4(255, true, 0, false, false, false, false, false),

    // optional extended tile set
    BEACH_N(256, false, 100, true, false, false, false, false),
    BEACH_NE(257, false, 100, true, false, false, false, false),
    BEACH_E(258, false, 100, true, false, false, false, false),
    BEACH_SE(259, false, 100, true, false, false, false, false),
    BEACH_S(260, false, 100, true, false, false, false, false),
    BEACH_SW(261, false, 100, true, false, false, false, false),
    BEACH_W(262, false, 100, true, false, false, false, false),
    BEACH_NW(263, false, 100, true, false, false, false, false),
    RIVER_NS(264, false, 0, true, false, false, false, false),
    RIVER_EW(265, false, 0, true, false, false, false, false),
    RIVER_NE(266, false, 0, true, false, false, false, false),
    RIVER_SE(267, false, 0, true, false, false, false, false),
    RIVER_SW(268, false, 0, true, false, false, false, false),
    RIVER_NW(269, false, 0, true, false, false, false, false),
    RIVER_NES(270, false, 0, true, false, false, false, false),
    RIVER_WSE(271, false, 0, true, false, false, false, false),
    RIVER_NWS(272, false, 0, true, false, false, false, false),
    RIVER_WNE(273, false, 0, true, false, false, false, false),
    RIVER_END_NORTH(274, false, 0, true, false, false, false, false),
    RIVER_END_EAST(275, false, 0, true, false, false, false, false),
    RIVER_END_SOUTH(276, false, 0, true, false, false, false, false),
    RIVER_END_WEST(277, false, 0, true, false, false, false, false),
    CASTLE_BRITANNIA_NW(278, false, 0, false, false, false, false, false),
    CASTLE_BRITANNIA_N(279, false, 0, false, false, false, false, false),
    CASTLE_BRITANNIA_NE(280, false, 0, false, false, false, false, false);

    private final int index;

    private final boolean requiresTransparency;

    private final int walkability;

    private final boolean isGroundTile;

    private final boolean isPortal;

    private final boolean isOpaque;

    private final boolean canTalkThrough;

    private final boolean isRenderedAtopNearbyGroundTile;
    
    Tile(int index, boolean requiresTransparency, int walkability, boolean isGroundTile, boolean isPortal, boolean isOpaque, boolean canTalkThrough, boolean isRenderedAtopNearbyGroundTile) {
        this.index = index;
        this.requiresTransparency = requiresTransparency;
        this.isGroundTile = isGroundTile;
        this.isPortal = isPortal;
        this.walkability = walkability;
        this.isOpaque = isOpaque;
        this.canTalkThrough = canTalkThrough;
        this.isRenderedAtopNearbyGroundTile = isRenderedAtopNearbyGroundTile;
    }

    public static Tile forIndex(int index) {
        return Stream.of(values()).filter(tile -> tile.index() == index).findAny().orElseThrow(IllegalArgumentException::new);
    }

    public int index() {
        return index;
    }

    public boolean requiresTransparency() {
        return requiresTransparency;
    }

    public int walkability() {
        return walkability;
    }

    public boolean isGroundTile() {
        return isGroundTile;
    }

    public boolean isPortal() {
        return isPortal;
    }

    public boolean isOpaque() {
        return isOpaque;
    }

    public boolean canTalkThrough() {
        return canTalkThrough;
    }

    public boolean isRenderedAtopNearbyGroundTile() {
        return isRenderedAtopNearbyGroundTile;
    }
}
