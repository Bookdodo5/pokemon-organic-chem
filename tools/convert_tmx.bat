@echo off
REM TMX Converter batch file for PokemonOrganicChem
REM Usage: convert_tmx.bat [input.tmx] [output_directory] OR convert_tmx.bat --all

if "%1"=="--all" (
    echo Converting ALL TMX files...
    python tmx_converter.py --all
    if %errorlevel% neq 0 (
        echo Conversion failed!
        pause
        exit /b 1
    )
    echo All conversions completed!
    pause
    exit /b 0
)

if "%2"=="" (
    echo Usage: convert_tmx.bat input.tmx output_directory
    echo    OR: convert_tmx.bat --all
    echo.
    echo Examples:
    echo   convert_tmx.bat ..\res\data\maps\porbital_town.tmx ..\res\data\maps\porbitalTown
    echo   convert_tmx.bat --all
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