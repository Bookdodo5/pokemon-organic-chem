package tile;

import entity.FacingDirections;

public class CollisionChecker {

    public CollisionTypes checkTileCollision(int x, int y, TileManager obstacleLayer, FacingDirections direction) {
        if (x < 0 || y < 0 || x >= obstacleLayer.maxLayerCol || y >= obstacleLayer.maxLayerRow) {
            return CollisionTypes.WALKABLE;
        }

        Tile tile = obstacleLayer.getTile(x, y);
        Tile prevTile = obstacleLayer.getTile(x - direction.getX(), y - direction.getY());

        return isDirectionalCollisionAllowed(tile, prevTile, direction);
    }

    private CollisionTypes isDirectionalCollisionAllowed(Tile tile, Tile prevTile, FacingDirections direction) {
        if (tile == null || prevTile == null) {
            return CollisionTypes.WALKABLE;
        }

        for (CollisionTypes collisionType : tile.getCollisionTypes()) {
            if (collisionType == null) {
                continue;
            }

            boolean result = switch (collisionType) {
                case BLOCKED -> false;
                case JUMP_N -> direction == FacingDirections.DOWN;
                case JUMP_S -> direction == FacingDirections.UP;
                case JUMP_E -> direction == FacingDirections.LEFT;
                case JUMP_W -> direction == FacingDirections.RIGHT;
                case CLIFF_N -> direction != FacingDirections.DOWN;
                case CLIFF_S -> direction != FacingDirections.UP;
                case CLIFF_E -> direction != FacingDirections.LEFT;
                case CLIFF_W -> direction != FacingDirections.RIGHT;
                default -> true;
            };

            if (!result) return CollisionTypes.BLOCKED;
        }

        for (CollisionTypes collisionType : prevTile.getCollisionTypes()) {
            if (collisionType == null) {
                continue;
            }

            boolean result = switch (collisionType) {
                case BLOCKED ->
                    false;
                case CLIFF_N ->
                    direction != FacingDirections.UP;
                case CLIFF_S ->
                    direction != FacingDirections.DOWN;
                case CLIFF_E ->
                    direction != FacingDirections.RIGHT;
                case CLIFF_W ->
                    direction != FacingDirections.LEFT;
                default ->
                    true;
            };

            if (!result) return CollisionTypes.BLOCKED;
        }

        return CollisionTypes.WALKABLE;
    }

    public CollisionTypes checkCollision(int x, int y, MapManager mapManager, FacingDirections direction) {
        TileManager obstacleLayer = mapManager.getCurrentLayers()[2];

        if (x >= 0 && y >= 0 && x < obstacleLayer.maxLayerCol && y < obstacleLayer.maxLayerRow) {
            CollisionTypes result = checkTileCollision(x, y, obstacleLayer, direction);

            if (result == CollisionTypes.WALKABLE && isJumpTile(obstacleLayer.getTile(x, y), direction)) {
                if (checkTileCollision(x + direction.getX(), y + direction.getY(), obstacleLayer, direction) == CollisionTypes.WALKABLE) {
                    return CollisionTypes.CAN_JUMP;
                } else return CollisionTypes.BLOCKED;
            }

            return result;
        }

        int globalX = x + mapManager.getGlobalX();
        int globalY = y + mapManager.getGlobalY();

        for (MapData map : mapManager.getVisibleMaps()) {
            if (globalX >= map.getGlobalX() && globalX < map.getGlobalX() + map.getWidth()
                    && globalY >= map.getGlobalY() && globalY < map.getGlobalY() + map.getHeight()) {

                int localX = globalX - map.getGlobalX();
                int localY = globalY - map.getGlobalY();
                TileManager obstacleLayer2 = map.getLayers()[2];
                CollisionTypes result = checkTileCollision(localX, localY, obstacleLayer2, direction);

                if (result == CollisionTypes.WALKABLE && isJumpTile(obstacleLayer2.getTile(localX, localY), direction)) {
                    if(checkTileCollision(localX + direction.getX(), localY + direction.getY(), obstacleLayer2, direction) == CollisionTypes.WALKABLE) {
                        return CollisionTypes.CAN_JUMP;
                    } else return CollisionTypes.BLOCKED;
                }

                return result;
            }
        }

        return CollisionTypes.WALKABLE;
    }

    private boolean isJumpTile(Tile tile, FacingDirections direction) {
        if (tile == null) {
            return false;
        }

        return switch (direction) {
            case DOWN ->
                tile.getCollisionTypes().contains(CollisionTypes.JUMP_N);
            case UP ->
                tile.getCollisionTypes().contains(CollisionTypes.JUMP_S);
            case LEFT ->
                tile.getCollisionTypes().contains(CollisionTypes.JUMP_E);
            case RIGHT ->
                tile.getCollisionTypes().contains(CollisionTypes.JUMP_W);
        };
    }
}
