#!/usr/bin/env python3
"""
TMX to text file converter for PokemonOrganicChem
Converts Tiled TMX map files to the text file format used by the game
"""

import sys
import os
import xml.etree.ElementTree as ET
from pathlib import Path
import glob
import re

def convert_tmx(tmx_file, output_dir):
    """Convert TMX file to individual layer text files"""
    
    # Parse the TMX file
    try:
        tree = ET.parse(tmx_file)
        root = tree.getroot()
    except Exception as e:
        print(f"Error parsing TMX file: {e}")
        return False
    
    # Get map dimensions
    width = int(root.get('width'))
    height = int(root.get('height'))
    
    print(f"Converting TMX file: {tmx_file}")
    print(f"Map dimensions: {width}x{height}")
    
    # Create output directory
    output_path = Path(output_dir)
    output_path.mkdir(parents=True, exist_ok=True)
    
    # Process each layer
    layers = root.findall('layer')
    print(f"Found {len(layers)} layers")
    
    for layer in layers:
        layer_name = layer.get('name')
        
        # Find the data element
        data_element = layer.find('data')
        if data_element is None:
            print(f"Warning: No data found for layer {layer_name}")
            continue
            
        encoding = data_element.get('encoding', '')
        
        if encoding == 'csv':
            csv_data = data_element.text.strip()
            process_csv_data(csv_data, layer_name, output_path, width, height)
        else:
            print(f"Warning: Unsupported encoding '{encoding}' for layer {layer_name}")
    
    return True

def process_csv_data(csv_data, layer_name, output_path, width, height):
    """Process CSV data and write to text file"""
    
    # Clean up CSV data and split by commas
    values = [val.strip() for val in csv_data.replace('\n', '').replace(' ', '').split(',') if val.strip()]
    
    # Create output file with consistent naming
    # Use title case for layer names to match existing conventions
    layer_filename = layer_name.title() + ".txt"
    output_file = output_path / layer_filename
    
    try:
        with open(output_file, 'w') as f:
            index = 0
            for row in range(height):
                line_values = []
                for col in range(width):
                    if index < len(values):
                        line_values.append(values[index])
                        index += 1
                    else:
                        line_values.append('0')  # Default value if data is missing
                
                f.write(','.join(line_values) + '\n')
        
        print(f"Created: {output_file}")
        
    except Exception as e:
        print(f"Error writing file {output_file}: {e}")

def normalize_directory_name(tmx_filename):
    """
    Convert all directory names to snake_case for consistency.
    """
    base_name = tmx_filename.stem
    
    # If it's already snake_case, return as is
    if '_' in base_name or base_name.islower():
        return base_name
    
    # Convert camelCase to snake_case
    # This handles cases like 'methanopolis' -> 'methanopolis' (already snake_case)
    # and 'porbitalTown' -> 'porbital_town'
    snake_case = re.sub(r'([a-z])([A-Z])', r'\1_\2', base_name).lower()
    return snake_case

def convert_all_tmx_files():
    """Convert all TMX files in the res/data/maps directory"""
    
    # Get the path to the maps directory
    script_dir = Path(__file__).parent
    maps_dir = script_dir.parent / "res" / "data" / "maps"
    
    if not maps_dir.exists():
        print(f"Error: Maps directory not found at {maps_dir}")
        return False
    
    # Find all TMX files
    tmx_files = list(maps_dir.glob("*.tmx"))
    
    if not tmx_files:
        print("No TMX files found in the maps directory")
        return False
    
    print(f"Found {len(tmx_files)} TMX files to convert:")
    for tmx_file in tmx_files:
        print(f"  - {tmx_file.name}")
    
    print("\nStarting conversion...")
    
    success_count = 0
    failed_count = 0
    
    for tmx_file in tmx_files:
        # Generate output directory name using the normalization function
        output_dir_name = normalize_directory_name(tmx_file)
        output_dir = maps_dir / output_dir_name
        
        print(f"\n--- Converting {tmx_file.name} to {output_dir.name} ---")
        
        if convert_tmx(tmx_file, output_dir):
            success_count += 1
            print(f"✓ Successfully converted {tmx_file.name}")
        else:
            failed_count += 1
            print(f"✗ Failed to convert {tmx_file.name}")
    
    print(f"\n=== Conversion Summary ===")
    print(f"Successfully converted: {success_count} files")
    print(f"Failed conversions: {failed_count} files")
    print(f"Total files processed: {len(tmx_files)}")
    
    return failed_count == 0

def main():
    if len(sys.argv) == 2 and sys.argv[1] == "--all":
        # Convert all TMX files
        if convert_all_tmx_files():
            print("\nAll conversions completed successfully!")
        else:
            print("\nSome conversions failed!")
            sys.exit(1)
    elif len(sys.argv) == 3:
        # Convert single TMX file
        input_file = sys.argv[1]
        output_dir = sys.argv[2]
        
        if not os.path.exists(input_file):
            print(f"Error: Input file '{input_file}' does not exist")
            sys.exit(1)
        
        if convert_tmx(input_file, output_dir):
            print("Conversion completed successfully!")
        else:
            print("Conversion failed!")
            sys.exit(1)
    else:
        print("Usage: python tmx_converter.py <input.tmx> <output_directory>")
        print("   OR: python tmx_converter.py --all")
        print("\nExamples:")
        print("  python tmx_converter.py ../res/data/maps/porbital_town.tmx ../res/data/maps/porbital_town")
        print("  python tmx_converter.py --all")
        sys.exit(1)

if __name__ == "__main__":
    main() 