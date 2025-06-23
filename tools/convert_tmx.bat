@echo off
REM TMX Converter batch file for PokemonOrganicChem
REM Usage: convert_tmx.bat input.tmx output_directory

if "%2"=="" (
    echo Usage: convert_tmx.bat input.tmx output_directory
    echo Example: convert_tmx.bat ..\res\data\maps\porbital_town.tmx ..\res\data\maps\porbitalTown
    pause
    exit /b 1
)

echo Converting TMX file...
python tmx_converter.py %1 %2

if %errorlevel% neq 0 (
    echo Conversion failed!
    pause
    exit /b 1
)

echo Conversion completed!
pause 