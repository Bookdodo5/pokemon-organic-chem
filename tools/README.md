# TMX Converter Tool

This tool converts Tiled TMX map files into the text format used by PokemonOrganicChem.

## Requirements

- Python 3.6 or higher

## Usage (100% AI generated)

### Option 1: Convert a single TMX file
```bash
python tmx_converter.py <input.tmx> <output_directory>
```

### Option 2: Convert ALL TMX files at once
```bash
python tmx_converter.py --all
```

### Option 3: Using the batch file (Windows)
```bash
# Convert single file
convert_tmx.bat <input.tmx> <output_directory>

# Convert all files
convert_tmx.bat --all
```

## Examples

### Convert a single map:
```bash
python tmx_converter.py ../res/data/maps/porbital_town.tmx ../res/data/maps/porbitalTown
```

### Convert all maps at once:
```bash
python tmx_converter.py --all
```

### Using batch file:
```bash
# Single file
convert_tmx.bat ..\res\data\maps\porbital_town.tmx ..\res\data\maps\porbitalTown

# All files
convert_tmx.bat --all
```

## What it does

The converter:
1. Reads the TMX file and extracts layer information
2. Creates the output directory if it doesn't exist
3. Converts each layer's CSV data into separate `.txt` files
4. Formats the data exactly like your existing map files

When using `--all`, it automatically:
- Finds all `.tmx` files in the `res/data/maps` directory
- Generates appropriate output directory names
- Converts all files in sequence
- Provides a summary of successful and failed conversions

## Output

For each layer in the TMX file, it creates a corresponding `.txt` file:
- `ground.txt` - Ground layer tiles
- `decoration.txt` - Decoration layer tiles  
- `obstacle.txt` - Obstacle/collision layer tiles
- `air.txt` - Air/overhead layer tiles

The output format matches your existing map data structure with comma-separated values and one row per line.

## Automatic Directory Naming

When using `--all`, the tool automatically creates output directories with these naming conventions:
- `porbital_town.tmx` → `porbitalTown/`
- `hallogue_town.tmx` → `hallogue_town/`
- `pyrrole_town.tmx` → `pyrrole_town/`
- `route1.tmx` → `route1/`
- `route2.tmx` → `route2/`
- `route3.tmx` → `route3/`
- `methanopolis.tmx` → `methanopolis/` 