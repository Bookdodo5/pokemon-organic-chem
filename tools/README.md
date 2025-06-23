# TMX Converter Tool

This tool converts Tiled TMX map files into the text format used by PokemonOrganicChem.

## Requirements

- Python 3.6 or higher

## Usage (100% AI generated)

### Option 1: Direct Python command
```bash
python tmx_converter.py <input.tmx> <output_directory>
```

### Option 2: Using the batch file (Windows)
```bash
convert_tmx.bat <input.tmx> <output_directory>
```

## Examples

Convert the porbital_town map:
```bash
python tmx_converter.py ../res/data/maps/porbital_town.tmx ../res/data/maps/porbitalTown
```

Or using the batch file:
```bash
convert_tmx.bat ..\res\data\maps\porbital_town.tmx ..\res\data\maps\porbitalTown
```

## What it does

The converter:
1. Reads the TMX file and extracts layer information
2. Creates the output directory if it doesn't exist
3. Converts each layer's CSV data into separate `.txt` files
4. Formats the data exactly like your existing map files

## Output

For each layer in the TMX file, it creates a corresponding `.txt` file:
- `ground.txt` - Ground layer tiles
- `decoration.txt` - Decoration layer tiles  
- `obstacle.txt` - Obstacle/collision layer tiles
- `air.txt` - Air/overhead layer tiles

The output format matches your existing map data structure with comma-separated values and one row per line. 