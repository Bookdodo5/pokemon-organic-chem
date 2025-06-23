#!/usr/bin/env python3
"""
TMX to text file converter for PokemonOrganicChem
Converts Tiled TMX map files to the text file format used by the game
"""

import sys
import os
import xml.etree.ElementTree as ET
from pathlib import Path

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
    
    # Create output file
    output_file = output_path / f"{layer_name}.txt"
    
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

def main():
    if len(sys.argv) != 3:
        print("Usage: python tmx_converter.py <input.tmx> <output_directory>")
        print("Example: python tmx_converter.py ../res/data/maps/porbital_town.tmx ../res/data/maps/porbitalTown")
        sys.exit(1)
    
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

if __name__ == "__main__":
    main() 